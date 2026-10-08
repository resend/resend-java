package com.resend.core.net.impl;

import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.RequestOptions;
import okhttp3.*;
import okio.Buffer;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLHandshakeException;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class HttpClientRetryTest {

    private static final MediaType JSON = MediaType.get("application/json");

    @Test
    public void testRetry_429ThenSuccess_ReturnsSuccess() {
        Script script = new Script(status(429), status(200));
        RecordingClient client = client(script, 2);

        AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(200, response.getCode());
        assertTrue(response.isSuccessful());
        assertEquals(2, script.requests.size());
        assertEquals(1, client.sleeps.size());
    }

    @Test
    public void testRetry_ServerErrorUntilExhausted_ReturnsLastResponse() {
        Script script = new Script(status(503));
        RecordingClient client = client(script, 2);

        AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(503, response.getCode());
        assertFalse(response.isSuccessful());
        assertEquals(3, script.requests.size());
        assertEquals(2, client.sleeps.size());
    }

    @Test
    public void testRetry_ClientErrorsOtherThan429_AreNotRetried() {
        for (int code : new int[]{400, 401, 403, 404, 409, 422}) {
            Script script = new Script(status(code), status(200));
            RecordingClient client = client(script, 3);

            AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

            assertEquals(code, response.getCode());
            assertEquals(1, script.requests.size(), "status " + code);
            assertTrue(client.sleeps.isEmpty());
        }
    }

    @Test
    public void testRetry_DisabledByDefault() {
        Script script = new Script(status(429), status(200));
        HttpClient client = new HttpClient(okHttp(script), "http://localhost:8080");

        AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(429, response.getCode());
        assertEquals(1, script.requests.size());
        assertEquals(0, client.getMaxRetries());
    }

    @Test
    public void testRetry_RetryAfterSecondsIsHonored() {
        Script script = new Script(status(429, "2"), status(200));
        RecordingClient client = client(script, 1);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(Collections.singletonList(2000L), client.sleeps);
    }

    @Test
    public void testRetry_RetryAfterIsCapped() {
        Script script = new Script(status(429, "120"), status(200));
        RecordingClient client = client(script, 1);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(Collections.singletonList(30_000L), client.sleeps);
    }

    @Test
    public void testRetry_RetryAfterZeroRetriesImmediately() {
        Script script = new Script(status(429, "0"), status(200));
        RecordingClient client = client(script, 1);

        AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(200, response.getCode());
        assertTrue(client.sleeps.isEmpty());
    }

    @Test
    public void testRetry_RetryAfterHttpDateIsHonored() {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String tenSecondsAhead = format.format(new Date(System.currentTimeMillis() + 10_000L));
        Script script = new Script(status(503, tenSecondsAhead), status(200));
        RecordingClient client = client(script, 1);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(1, client.sleeps.size());
        assertTrue(client.sleeps.get(0) > 7_000L && client.sleeps.get(0) <= 10_000L, "slept " + client.sleeps.get(0));
    }

    @Test
    public void testRetry_PastRetryAfterDateFallsBackToBackoff() {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String past = format.format(new Date(System.currentTimeMillis() - 60_000L));
        Script script = new Script(status(503, past), status(200));
        RecordingClient client = client(script, 1);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertBackoff(client.sleeps.get(0), 0);
    }

    @Test
    public void testRetry_InvalidRetryAfterFallsBackToBackoff() {
        Script script = new Script(status(429, "soon"), status(200));
        RecordingClient client = client(script, 1);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertBackoff(client.sleeps.get(0), 0);
    }

    @Test
    public void testRetry_BackoffGrowsExponentiallyWithJitterAndIsCapped() {
        Script script = new Script(status(503));
        RecordingClient client = client(script, 6);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(6, client.sleeps.size());
        for (int attempt = 0; attempt < client.sleeps.size(); attempt++) {
            assertBackoff(client.sleeps.get(attempt), attempt);
        }
    }

    @Test
    public void testRetry_NetworkErrorIsRetried() {
        Script script = new Script(failure(new ConnectException("refused")), status(200));
        RecordingClient client = client(script, 1);

        AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(200, response.getCode());
        assertEquals(2, script.requests.size());
        assertEquals(1, client.sleeps.size());
        assertBackoff(client.sleeps.get(0), 0);
    }

    @Test
    public void testRetry_NetworkErrorUntilExhausted_ThrowsRuntimeExceptionWithCause() {
        ConnectException cause = new ConnectException("refused");
        Script script = new Script(failure(cause));
        RecordingClient client = client(script, 2);

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> client.perform("/emails", "re_test", HttpMethod.GET, null, null));

        assertSame(cause, thrown.getCause());
        assertEquals(3, script.requests.size());
    }

    @Test
    public void testRetry_TransientConnectionFailuresAreRetried() {
        List<IOException> transientFailures = Arrays.<IOException>asList(
                new ConnectException("refused"),
                new SocketException("Connection reset"),
                new SocketException("Broken pipe"),
                new EOFException("unexpected end of stream"));

        for (IOException failure : transientFailures) {
            Script script = new Script(failure(failure), status(200));
            RecordingClient client = client(script, 1);

            AbstractHttpResponse<String> response = client.perform("/emails", "re_test", HttpMethod.GET, null, null);

            assertEquals(200, response.getCode(), failure.toString());
            assertEquals(2, script.requests.size(), failure.toString());
            assertEquals(1, client.sleeps.size(), failure.toString());
        }
    }

    @Test
    public void testRetry_FailuresThatRetryingCannotFixAreNotRetried() {
        List<IOException> permanentFailures = Arrays.<IOException>asList(
                new UnknownHostException("api.resend.com"),
                new SSLHandshakeException("PKIX path building failed"),
                new ProtocolException("unexpected status line"),
                new IOException("something else"));

        for (IOException failure : permanentFailures) {
            Script script = new Script(failure(failure), status(200));
            RecordingClient client = client(script, 3);

            RuntimeException thrown = assertThrows(RuntimeException.class,
                    () -> client.perform("/emails", "re_test", HttpMethod.GET, null, null));

            assertSame(failure, thrown.getCause());
            assertEquals(1, script.requests.size(), failure.toString());
            assertTrue(client.sleeps.isEmpty(), failure.toString());
        }
    }

    @Test
    public void testRetry_OversizedRetryAfterIsCappedInsteadOfOverflowing() {
        for (String retryAfter : new String[]{"9223372036854775807", "9223372036854776", "99999999999999999999",
                "1000000000", "31"}) {
            Script script = new Script(status(429, retryAfter), status(200));
            RecordingClient client = client(script, 1);

            client.perform("/emails", "re_test", HttpMethod.GET, null, null);

            assertEquals(Collections.singletonList(30_000L), client.sleeps, "Retry-After " + retryAfter);
        }
    }

    @Test
    public void testRetry_NegativeRetryAfterFallsBackToBackoff() {
        Script script = new Script(status(429, "-5"), status(200));
        RecordingClient client = client(script, 1);

        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertBackoff(client.sleeps.get(0), 0);
    }

    @Test
    public void testRetry_TimeoutsAreNotRetried() {
        List<IOException> timeouts = Arrays.<IOException>asList(
                new SocketTimeoutException("connect timed out"),
                new InterruptedIOException("timeout"));

        for (IOException timeout : timeouts) {
            Script script = new Script(failure(timeout), status(200));
            RecordingClient client = client(script, 3);

            RuntimeException thrown = assertThrows(RuntimeException.class,
                    () -> client.perform("/emails", "re_test", HttpMethod.GET, null, null));

            assertSame(timeout, thrown.getCause());
            assertEquals(1, script.requests.size());
            assertTrue(client.sleeps.isEmpty());
        }
    }

    @Test
    public void testRetry_PerRequestCallTimeoutIsNotRetried() {
        Script script = new Script(status(200));
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    script.requests.add(chain.request());
                    throw new InterruptedIOException("timeout");
                })
                .build();
        RecordingClient client = new RecordingClient(okHttp, 3);
        RequestOptions options = RequestOptions.builder().timeout(java.time.Duration.ofSeconds(1)).build();

        assertThrows(RuntimeException.class,
                () -> client.perform("/emails", "re_test", HttpMethod.GET, null, null, options));

        assertEquals(1, script.requests.size());
    }

    @Test
    public void testRetry_RealCallAndReadTimeoutsAgainstSilentServer_AreNotRetried() throws IOException {
        try (SilentServer server = new SilentServer()) {
            OkHttpClient callTimeoutClient = new OkHttpClient.Builder().callTimeout(java.time.Duration.ofMillis(300)).build();
            OkHttpClient readTimeoutClient = new OkHttpClient.Builder().readTimeout(java.time.Duration.ofMillis(300)).build();
            List<OkHttpClient> clients = Arrays.asList(callTimeoutClient, readTimeoutClient);

            for (OkHttpClient okHttp : clients) {
                int before = server.accepted.get();
                RecordingClient client = new RecordingClient(okHttp, "http://localhost:" + server.port(), 3);

                RuntimeException thrown = assertThrows(RuntimeException.class,
                        () -> client.perform("/emails", "re_test", HttpMethod.GET, null, null));

                assertTrue(thrown.getCause() instanceof InterruptedIOException,
                        "cause was " + thrown.getCause());
                assertTrue(client.sleeps.isEmpty());
                assertTrue(server.awaitAccepted(before + 1), "the server never saw the connection");
                assertEquals(before + 1, server.accepted.get());
            }
        }
    }

    @Test
    public void testRetry_PostWithoutIdempotencyKey_RetriesOnlyOn429() {
        Script serverError = new Script(status(503), status(200));
        RecordingClient serverErrorClient = client(serverError, 3);
        AbstractHttpResponse<String> failed =
                serverErrorClient.perform("/emails", "re_test", HttpMethod.POST, "{}", JSON);
        assertEquals(503, failed.getCode());
        assertEquals(1, serverError.requests.size());

        Script networkError = new Script(failure(new ConnectException("reset")), status(200));
        RecordingClient networkErrorClient = client(networkError, 3);
        assertThrows(RuntimeException.class,
                () -> networkErrorClient.perform("/emails", "re_test", HttpMethod.POST, "{}", JSON));
        assertEquals(1, networkError.requests.size());

        Script rateLimited = new Script(status(429), status(200));
        RecordingClient rateLimitedClient = client(rateLimited, 3);
        AbstractHttpResponse<String> ok = rateLimitedClient.perform("/emails", "re_test", HttpMethod.POST, "{}", JSON);
        assertEquals(200, ok.getCode());
        assertEquals(2, rateLimited.requests.size());
    }

    @Test
    public void testRetry_PostWithIdempotencyKey_RetriesServerAndNetworkErrors() {
        RequestOptions options = RequestOptions.builder().setIdempotencyKey("key-1").build();

        Script serverError = new Script(status(503), status(200));
        AbstractHttpResponse<String> response =
                client(serverError, 3).perform("/emails", "re_test", HttpMethod.POST, "{}", JSON, options);
        assertEquals(200, response.getCode());
        assertEquals(2, serverError.requests.size());
        assertEquals("key-1", serverError.requests.get(1).header("Idempotency-Key"));

        Script networkError = new Script(failure(new ConnectException("reset")), status(200));
        AbstractHttpResponse<String> recovered =
                client(networkError, 3).perform("/emails", "re_test", HttpMethod.POST, "{}", JSON, options);
        assertEquals(200, recovered.getCode());
        assertEquals(2, networkError.requests.size());
    }

    @Test
    public void testRetry_PostWithIdempotencyKeyInAdditionalHeaders_IsRetried() {
        RequestOptions options = RequestOptions.builder().add("Idempotency-Key", "key-2").build();
        Script script = new Script(status(500), status(200));

        AbstractHttpResponse<String> response =
                client(script, 1).perform("/emails", "re_test", HttpMethod.POST, "{}", JSON, options);

        assertEquals(200, response.getCode());
        assertEquals(2, script.requests.size());
    }

    @Test
    public void testRetry_NonPostMethods_RetryServerErrors() {
        for (HttpMethod method : new HttpMethod[]{HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PATCH}) {
            String payload = method == HttpMethod.GET ? null : "{}";
            Script script = new Script(status(502), status(200));

            AbstractHttpResponse<String> response =
                    client(script, 1).perform("/emails/1", "re_test", method, payload, JSON);

            assertEquals(200, response.getCode(), method.name());
            assertEquals(2, script.requests.size(), method.name());
        }
    }

    @Test
    public void testRetry_RequestMaxRetriesOverridesClientDefault() {
        RequestOptions enabled = RequestOptions.builder().maxRetries(2).build();
        Script enabledScript = new Script(status(503), status(503), status(200));
        AbstractHttpResponse<String> response =
                client(enabledScript, 0).perform("/emails", "re_test", HttpMethod.GET, null, null, enabled);
        assertEquals(200, response.getCode());
        assertEquals(3, enabledScript.requests.size());

        RequestOptions disabled = RequestOptions.builder().maxRetries(0).build();
        Script disabledScript = new Script(status(503), status(200));
        AbstractHttpResponse<String> failed =
                client(disabledScript, 3).perform("/emails", "re_test", HttpMethod.GET, null, null, disabled);
        assertEquals(503, failed.getCode());
        assertEquals(1, disabledScript.requests.size());
    }

    @Test
    public void testRetry_RequestOptionsWithoutMaxRetriesInheritClientDefault() {
        Script script = new Script(status(503), status(200));

        AbstractHttpResponse<String> response = client(script, 2)
                .perform("/emails", "re_test", HttpMethod.GET, null, null, RequestOptions.builder().build());

        assertEquals(200, response.getCode());
        assertEquals(2, script.requests.size());
    }

    @Test
    public void testRetry_MultipartBodyIsResentOnRetry() {
        Script script = new Script(status(503), status(200));
        RequestOptions options = RequestOptions.builder().setIdempotencyKey("key-3").build();

        AbstractHttpResponse<String> response = client(script, 1).performMultipart(
                "/contacts/imports", "re_test", HttpMethod.POST, new byte[]{'a', ',', 'b'}, "contacts.csv",
                MediaType.get("text/csv"), Collections.singletonMap("segment_id", "seg_1"), options);

        assertEquals(200, response.getCode());
        assertEquals(2, script.bodies.size());
        assertTrue(script.bodies.get(0).contains("a,b"));
        assertTrue(script.bodies.get(0).contains("seg_1"));
        assertEquals(contentWithoutBoundary(script.bodies.get(0)), contentWithoutBoundary(script.bodies.get(1)));
    }

    @Test
    public void testRetry_PerRequestTimeoutIsAppliedToEveryAttempt() {
        List<Long> timeouts = new ArrayList<>();
        Script script = new Script(status(503), status(200));
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    timeouts.add(chain.call().timeout().timeoutNanos());
                    return script.intercept(chain);
                })
                .build();
        RecordingClient client = new RecordingClient(okHttp, 1);
        RequestOptions options = RequestOptions.builder().timeout(java.time.Duration.ofSeconds(4)).build();

        client.perform("/emails", "re_test", HttpMethod.GET, null, null, options);

        long expected = java.time.Duration.ofSeconds(4).toNanos();
        assertEquals(Arrays.asList(expected, expected), timeouts);
    }

    @Test
    public void testRetry_InterruptedWhileWaiting_RestoresInterruptFlagAndThrows() {
        Script script = new Script(status(503), status(200));
        HttpClient client = new HttpClient(okHttp(script), "http://localhost:8080", 1) {
            @Override
            void sleep(final long millis) throws InterruptedException {
                throw new InterruptedException();
            }
        };

        try {
            RuntimeException thrown = assertThrows(RuntimeException.class,
                    () -> client.perform("/emails", "re_test", HttpMethod.GET, null, null));

            assertTrue(thrown.getCause() instanceof InterruptedException);
            assertTrue(Thread.currentThread().isInterrupted());
            assertEquals(1, script.requests.size());
        } finally {
            Thread.interrupted();
        }
    }

    @Test
    public void testConstructor_RejectsNegativeMaxRetries() {
        assertThrows(IllegalArgumentException.class,
                () -> new HttpClient(new OkHttpClient(), "http://localhost:8080", -1));
    }

    @Test
    public void testRequestOptions_MaxRetries() {
        assertNull(RequestOptions.builder().build().getMaxRetries());
        assertEquals(0, RequestOptions.builder().maxRetries(0).build().getMaxRetries());
        assertEquals(4, RequestOptions.builder().maxRetries(4).build().getMaxRetries());
        assertThrows(IllegalArgumentException.class, () -> RequestOptions.builder().maxRetries(-1));
    }

    private static void assertBackoff(final long actual, final int attempt) {
        long base = Math.min(5000L, 500L * (1L << attempt));
        long lowest = (long) (base * 0.75);
        assertTrue(actual >= lowest && actual <= base,
                "attempt " + attempt + " slept " + actual + "ms, expected between " + lowest + " and " + base);
    }

    private static String contentWithoutBoundary(final String body) {
        return body.replaceAll("--[A-Za-z0-9-]+", "--boundary");
    }

    private static RecordingClient client(final Script script, final int maxRetries) {
        return new RecordingClient(okHttp(script), maxRetries);
    }

    private static OkHttpClient okHttp(final Script script) {
        return new OkHttpClient.Builder().addInterceptor(script).build();
    }

    private static Step status(final int code) {
        return new Step(code, null, null);
    }

    private static Step status(final int code, final String retryAfter) {
        return new Step(code, retryAfter, null);
    }

    private static Step failure(final IOException failure) {
        return new Step(0, null, failure);
    }

    private static final class SilentServer implements AutoCloseable {

        private final ServerSocket socket;
        private final AtomicInteger accepted = new AtomicInteger();
        private final List<Socket> connections = Collections.synchronizedList(new ArrayList<Socket>());

        SilentServer() throws IOException {
            socket = new ServerSocket(0);
            Thread acceptor = new Thread(() -> {
                try {
                    while (!socket.isClosed()) {
                        connections.add(socket.accept());
                        accepted.incrementAndGet();
                    }
                } catch (IOException ignored) {
                    return;
                }
            });
            acceptor.setDaemon(true);
            acceptor.start();
        }

        int port() {
            return socket.getLocalPort();
        }

        boolean awaitAccepted(final int expected) {
            long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
            while (accepted.get() < expected && System.nanoTime() < deadline) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
            return accepted.get() >= expected;
        }

        @Override
        public void close() throws IOException {
            socket.close();
            synchronized (connections) {
                for (Socket connection : connections) {
                    connection.close();
                }
            }
        }
    }

    private static final class RecordingClient extends HttpClient {

        private final List<Long> sleeps = new ArrayList<>();

        RecordingClient(final OkHttpClient okHttpClient, final int maxRetries) {
            this(okHttpClient, "http://localhost:8080", maxRetries);
        }

        RecordingClient(final OkHttpClient okHttpClient, final String baseUrl, final int maxRetries) {
            super(okHttpClient, baseUrl, maxRetries);
        }

        @Override
        void sleep(final long millis) {
            sleeps.add(millis);
        }
    }

    private static final class Step {

        private final int code;
        private final String retryAfter;
        private final IOException failure;

        Step(final int code, final String retryAfter, final IOException failure) {
            this.code = code;
            this.retryAfter = retryAfter;
            this.failure = failure;
        }
    }

    private static final class Script implements Interceptor {

        private final List<Step> steps;
        private final List<Request> requests = new ArrayList<>();
        private final List<String> bodies = new ArrayList<>();

        Script(final Step... steps) {
            this.steps = Arrays.asList(steps);
        }

        @Override
        public Response intercept(final Chain chain) throws IOException {
            Request request = chain.request();
            Step step = steps.get(Math.min(requests.size(), steps.size() - 1));
            requests.add(request);
            if (request.body() != null) {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                bodies.add(buffer.readUtf8());
            }
            if (step.failure != null) {
                throw step.failure;
            }
            Response.Builder response = new Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(step.code)
                    .message("stub")
                    .body(ResponseBody.create("{}", JSON));
            if (step.retryAfter != null) {
                response.header("Retry-After", step.retryAfter);
            }
            return response.build();
        }
    }
}
