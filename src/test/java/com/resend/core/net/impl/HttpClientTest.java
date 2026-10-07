package com.resend.core.net.impl;

import com.resend.core.net.HttpMethod;
import com.resend.core.net.RequestOptions;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class HttpClientTest {

    @Test
    public void testDefaultConstructor_UsesResendApi() {
        assertEquals(HttpClient.BASE_API, new HttpClient().getBaseUrl());
    }

    @Test
    public void testGetDefault_ReturnsSameInstance() {
        assertSame(HttpClient.getDefault(), HttpClient.getDefault());
        assertEquals(HttpClient.BASE_API, HttpClient.getDefault().getBaseUrl());
    }

    @Test
    public void testBaseUrl_TrailingSlashesAreRemoved() {
        OkHttpClient okHttp = new OkHttpClient();

        assertEquals("http://localhost:8080", new HttpClient(okHttp, "http://localhost:8080/").getBaseUrl());
        assertEquals("https://proxy.example.com/resend",
                new HttpClient(okHttp, "https://proxy.example.com/resend//").getBaseUrl());
    }

    @Test
    public void testBaseUrl_RejectsInvalidValues() {
        OkHttpClient okHttp = new OkHttpClient();

        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, null));
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, ""));
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, "api.resend.com"));
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, "ftp://api.resend.com"));
    }

    @Test
    public void testBaseUrl_RejectsQueryAndFragment() {
        OkHttpClient okHttp = new OkHttpClient();

        // Endpoint paths are appended to the base URL, so these would end up inside the query or the fragment.
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, "https://proxy.example.com/api?tenant=x"));
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, "https://proxy.example.com/api?"));
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(okHttp, "https://proxy.example.com/api#frag"));
    }

    @Test
    public void testConstructor_RejectsNullOkHttpClient() {
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(null));
    }

    @Test
    public void testPerform_RequestTimeoutOverridesClientCallTimeout() {
        CapturingInterceptor capture = new CapturingInterceptor();
        HttpClient client = clientWith(capture, Duration.ofSeconds(30));
        RequestOptions options = RequestOptions.builder().timeout(Duration.ofMillis(1500)).build();

        client.perform("/emails", "re_test", HttpMethod.GET, null, null, options);

        assertEquals(Duration.ofMillis(1500).toNanos(), capture.callTimeoutNanos);
    }

    @Test
    public void testPerform_WithoutRequestTimeoutUsesClientCallTimeout() {
        CapturingInterceptor capture = new CapturingInterceptor();
        HttpClient client = clientWith(capture, Duration.ofSeconds(30));

        client.perform("/emails", "re_test", HttpMethod.GET, null, null, RequestOptions.builder().build());

        assertEquals(Duration.ofSeconds(30).toNanos(), capture.callTimeoutNanos);
    }

    @Test
    public void testPerform_ZeroRequestTimeoutDisablesCallTimeout() {
        CapturingInterceptor capture = new CapturingInterceptor();
        HttpClient client = clientWith(capture, Duration.ofSeconds(30));
        RequestOptions options = RequestOptions.builder().timeout(Duration.ZERO).build();

        client.perform("/emails", "re_test", HttpMethod.GET, null, null, options);

        assertEquals(0L, capture.callTimeoutNanos);
    }

    @Test
    public void testPerform_RequestTimeoutDoesNotLeakIntoLaterRequests() {
        CapturingInterceptor capture = new CapturingInterceptor();
        HttpClient client = clientWith(capture, Duration.ofSeconds(30));

        client.perform("/emails", "re_test", HttpMethod.GET, null, null,
                RequestOptions.builder().timeout(Duration.ofMillis(1500)).build());
        client.perform("/emails", "re_test", HttpMethod.GET, null, null);

        assertEquals(Duration.ofSeconds(30).toNanos(), capture.callTimeoutNanos);
    }

    @Test
    public void testPerform_RequestOptionsStillAddHeaders() {
        CapturingInterceptor capture = new CapturingInterceptor();
        HttpClient client = clientWith(capture, Duration.ZERO);
        RequestOptions options = RequestOptions.builder()
                .setIdempotencyKey("key-1")
                .add("X-Trace-Id", "trace-1")
                .timeout(Duration.ofSeconds(2))
                .build();

        client.perform("/emails", "re_test", HttpMethod.POST, "{}", MediaType.get("application/json"), options);

        assertEquals("key-1", capture.request.header("Idempotency-Key"));
        assertEquals("trace-1", capture.request.header("X-Trace-Id"));
        assertEquals("Bearer re_test", capture.request.header("Authorization"));
    }

    @Test
    public void testPerformMultipart_RequestTimeoutIsApplied() {
        CapturingInterceptor capture = new CapturingInterceptor();
        HttpClient client = clientWith(capture, Duration.ofSeconds(30));
        RequestOptions options = RequestOptions.builder().timeout(Duration.ofSeconds(5)).build();

        client.performMultipart("/contacts/imports", "re_test", HttpMethod.POST, new byte[]{1, 2}, "contacts.csv",
                MediaType.get("text/csv"), Collections.<String, String>emptyMap(), options);

        assertEquals(Duration.ofSeconds(5).toNanos(), capture.callTimeoutNanos);
    }

    @Test
    public void testRequestOptions_RejectsNegativeTimeout() {
        assertThrows(IllegalArgumentException.class,
                () -> RequestOptions.builder().timeout(Duration.ofSeconds(-1)));
    }

    @Test
    public void testRequestOptions_TimeoutDefaultsToNull() {
        assertNull(RequestOptions.builder().build().getTimeout());
    }

    private static HttpClient clientWith(final CapturingInterceptor capture, final Duration callTimeout) {
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .callTimeout(callTimeout)
                .addInterceptor(capture)
                .build();
        return new HttpClient(okHttp, "http://localhost:8080");
    }

    private static final class CapturingInterceptor implements Interceptor {

        private Request request;
        private long callTimeoutNanos = -1L;

        @Override
        public Response intercept(final Chain chain) {
            request = chain.request();
            callTimeoutNanos = chain.call().timeout().timeoutNanos();
            return new Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("stub")
                    .body(ResponseBody.create("{}", MediaType.get("application/json")))
                    .build();
        }
    }
}
