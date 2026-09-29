package com.resend;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.impl.HttpClient;
import com.resend.services.emails.Emails;
import com.resend.services.emails.model.CreateEmailResponse;
import com.resend.services.util.EmailsUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ResendTest {

    @Test
    public void testServices_ShareHttpClientAcrossCalls() {
        Resend resend = new Resend("test-api-key");

        assertSame(resend.emails().getHttpClient(), resend.emails().getHttpClient());
        assertSame(resend.emails().getHttpClient(), resend.domains().getHttpClient());
        assertSame(resend.contacts().getHttpClient(), resend.contacts().imports().getHttpClient());
    }

    @Test
    public void testServices_ShareHttpClientAcrossResendInstances() {
        assertSame(new Resend("key-a").emails().getHttpClient(), new Emails("key-b").getHttpClient());
    }

    @Test
    public void testBuilder_RequiresApiKey() {
        assertThrows(IllegalStateException.class, () -> Resend.builder().build());
        assertThrows(IllegalStateException.class, () -> Resend.builder().apiKey("  ").build());
    }

    @Test
    public void testBuilder_WithOnlyApiKey_UsesSharedDefaultClient() {
        Resend resend = Resend.builder().apiKey("test-api-key").build();

        assertSame(HttpClient.getDefault(), resend.emails().getHttpClient());
        assertSame(new Resend("other-key").emails().getHttpClient(), resend.emails().getHttpClient());
    }

    @Test
    public void testBuilder_ServicesAndSubServicesShareConfiguredClient() {
        Resend resend = Resend.builder()
                .apiKey("test-api-key")
                .baseUrl("http://localhost:8080")
                .build();

        IHttpClient<String> client = resend.emails().getHttpClient();
        assertNotSame(HttpClient.getDefault(), client);
        assertSame(client, resend.batch().getHttpClient());
        assertSame(client, resend.domains().claims().getHttpClient());
        assertSame(client, resend.contacts().imports().getHttpClient());
        assertSame(client, resend.suppressions().batch().getHttpClient());
    }

    @Test
    public void testBuilder_BaseUrlOnly_ReusesSharedOkHttpClient() {
        Resend resend = Resend.builder()
                .apiKey("re_test")
                .baseUrl("http://localhost:8080/resend/")
                .build();

        HttpClient client = (HttpClient) resend.emails().getHttpClient();

        assertEquals("http://localhost:8080/resend", client.getBaseUrl());
        assertSame(HttpClient.getDefault().getOkHttpClient(), client.getOkHttpClient());
    }

    @Test
    public void testBuilder_NullBaseUrl_MeansDefault() {
        Resend resend = Resend.builder().apiKey("re_test").baseUrl(null).build();

        assertSame(HttpClient.getDefault(), resend.emails().getHttpClient());
    }

    @Test
    public void testBuilder_AppliesTimeoutsAndProxyOnTopOfDefaultClient() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("proxy.internal", 3128));
        Resend resend = Resend.builder()
                .apiKey("re_test")
                .connectTimeout(Duration.ofSeconds(3))
                .readTimeout(Duration.ofSeconds(20))
                .writeTimeout(Duration.ofSeconds(15))
                .callTimeout(Duration.ofSeconds(60))
                .proxy(proxy)
                .build();

        HttpClient client = (HttpClient) resend.emails().getHttpClient();
        OkHttpClient okHttp = client.getOkHttpClient();
        OkHttpClient defaultOkHttp = HttpClient.getDefault().getOkHttpClient();

        assertEquals(HttpClient.BASE_API, client.getBaseUrl());
        assertEquals(3_000, okHttp.connectTimeoutMillis());
        assertEquals(20_000, okHttp.readTimeoutMillis());
        assertEquals(15_000, okHttp.writeTimeoutMillis());
        assertEquals(60_000, okHttp.callTimeoutMillis());
        assertEquals(proxy, okHttp.proxy());
        assertSame(defaultOkHttp.connectionPool(), okHttp.connectionPool());
        assertSame(defaultOkHttp.dispatcher(), okHttp.dispatcher());
        assertNull(defaultOkHttp.proxy(), "the shared default client must not change");
    }

    @Test
    public void testBuilder_DocumentedDefaultTimeouts() {
        OkHttpClient defaultOkHttp = HttpClient.getDefault().getOkHttpClient();

        assertEquals(10_000, defaultOkHttp.connectTimeoutMillis());
        assertEquals(10_000, defaultOkHttp.readTimeoutMillis());
        assertEquals(10_000, defaultOkHttp.writeTimeoutMillis());
        assertEquals(0, defaultOkHttp.callTimeoutMillis());
    }

    @Test
    public void testBuilder_CustomHttpClient_IsUsedForEveryRequest() throws ResendException {
        IHttpClient<String> custom = mock(IHttpClient.class);
        when(custom.perform(eq("/emails"), eq("re_test"), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"id\":\"49a3999c-0ce1-4ea6-ab68-afcd6dc2e794\"}", true));
        Resend resend = Resend.builder().apiKey("re_test").httpClient(custom).build();

        CreateEmailResponse response = resend.emails().send(EmailsUtil.createEmailOptions());

        assertEquals("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", response.getId());
        assertSame(custom, resend.emails().getHttpClient());
        assertSame(custom, resend.contacts().imports().getHttpClient());
    }

    @Test
    public void testBuilder_CustomHttpClient_CannotBeCombinedWithBuiltInClientOptions() {
        IHttpClient<String> custom = mock(IHttpClient.class);

        assertThrows(IllegalStateException.class, () -> Resend.builder().apiKey("re_test")
                .httpClient(custom).baseUrl("http://localhost:8080").build());
        assertThrows(IllegalStateException.class, () -> Resend.builder().apiKey("re_test")
                .httpClient(custom).readTimeout(Duration.ofSeconds(5)).build());
        assertThrows(IllegalStateException.class, () -> Resend.builder().apiKey("re_test")
                .httpClient(custom).proxy(Proxy.NO_PROXY).build());
    }

    @Test
    public void testBuilder_OkHttpAdapter_SendsRequestsToItsBaseUrl() throws ResendException {
        StubInterceptor stub = new StubInterceptor(200, "{\"id\":\"49a3999c-0ce1-4ea6-ab68-afcd6dc2e794\"}");
        OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(stub).build();
        Resend resend = Resend.builder()
                .apiKey("re_test")
                .httpClient(new HttpClient(okHttp, "http://localhost:8080/resend/"))
                .build();

        CreateEmailResponse response = resend.emails().send(EmailsUtil.createEmailOptions());

        assertEquals("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", response.getId());
        assertEquals(1, stub.requests.size());
        Request request = stub.requests.get(0);
        assertEquals("http://localhost:8080/resend/emails", request.url().toString());
        assertEquals("POST", request.method());
        assertEquals("Bearer re_test", request.header("Authorization"));
        assertEquals(HttpClient.USER_AGENT, request.header("User-Agent"));
    }

    @Test
    public void testBuilder_ErrorResponseThrowsResendException() {
        StubInterceptor stub = new StubInterceptor(422,
                "{\"statusCode\":422,\"name\":\"validation_error\",\"message\":\"Invalid `to` field.\"}");
        Resend resend = Resend.builder()
                .apiKey("re_test")
                .httpClient(new HttpClient(new OkHttpClient.Builder().addInterceptor(stub).build()))
                .build();

        ResendException exception = assertThrows(ResendException.class,
                () -> resend.emails().send(EmailsUtil.createEmailOptions()));

        assertEquals(422, exception.getStatusCode());
        assertEquals(HttpClient.BASE_API + "/emails", stub.requests.get(0).url().toString());
    }

    @Test
    public void testBuilder_RejectsInvalidBaseUrl() {
        assertThrows(IllegalArgumentException.class,
                () -> Resend.builder().apiKey("re_test").baseUrl("not a url").build());
        assertThrows(IllegalArgumentException.class,
                () -> Resend.builder().apiKey("re_test").baseUrl("https://proxy.example.com/api?tenant=x").build());
        assertThrows(IllegalArgumentException.class,
                () -> Resend.builder().apiKey("re_test").baseUrl("https://proxy.example.com/api#frag").build());
    }

    @Test
    public void testBuilder_RejectsNegativeTimeout() {
        assertThrows(IllegalArgumentException.class,
                () -> Resend.builder().readTimeout(Duration.ofSeconds(-1)));
    }

    /**
     * Records every request and answers it with a canned response instead of going to the network.
     */
    private static final class StubInterceptor implements Interceptor {

        private final List<Request> requests = new ArrayList<>();
        private final int code;
        private final String body;

        StubInterceptor(final int code, final String body) {
            this.code = code;
            this.body = body;
        }

        @Override
        public Response intercept(final Chain chain) {
            requests.add(chain.request());
            return new Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(code)
                    .message("stub")
                    .body(ResponseBody.create(body, MediaType.get("application/json")))
                    .build();
        }
    }
}
