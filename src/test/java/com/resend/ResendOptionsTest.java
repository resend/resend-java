package com.resend;

import com.resend.core.net.impl.HttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.emails.Emails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResendOptionsTest {

    @Test
    public void testDefaultsBuild() {
        ResendOptions options = ResendOptions.defaults();
        assertNull(options.getBaseUrl());
        assertNull(options.getUserAgent());
        assertNull(options.getConnectTimeoutMs());
        assertNull(options.getReadTimeoutMs());
        assertNull(options.getWriteTimeoutMs());
        assertNull(options.getCallTimeoutMs());
    }

    @Test
    public void testBuilderValues() {
        ResendOptions options = ResendOptions.builder()
                .baseUrl("https://example.test")
                .userAgent("my-app/1.0")
                .connectTimeoutMs(5_000L)
                .readTimeoutMs(10_000L)
                .writeTimeoutMs(15_000L)
                .callTimeoutMs(20_000L)
                .build();

        assertEquals("https://example.test", options.getBaseUrl());
        assertEquals("my-app/1.0", options.getUserAgent());
        assertEquals(5_000L, options.getConnectTimeoutMs());
        assertEquals(10_000L, options.getReadTimeoutMs());
        assertEquals(15_000L, options.getWriteTimeoutMs());
        assertEquals(20_000L, options.getCallTimeoutMs());
    }

    @Test
    public void testNegativeTimeoutRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> ResendOptions.builder().connectTimeoutMs(-1L).build());
        assertThrows(IllegalArgumentException.class,
                () -> ResendOptions.builder().readTimeoutMs(-1L).build());
        assertThrows(IllegalArgumentException.class,
                () -> ResendOptions.builder().writeTimeoutMs(-1L).build());
        assertThrows(IllegalArgumentException.class,
                () -> ResendOptions.builder().callTimeoutMs(-1L).build());
    }

    @Test
    public void testResendRequiresApiKey() {
        assertThrows(IllegalArgumentException.class, () -> new Resend(null));
        assertThrows(IllegalArgumentException.class, () -> new Resend(""));
        assertThrows(IllegalArgumentException.class, () -> new Resend("   "));
    }

    @Test
    public void testResendStoresOptions() {
        ResendOptions options = ResendOptions.builder()
                .baseUrl("https://example.test")
                .build();
        Resend resend = new Resend("re_test", options);
        assertEquals("https://example.test", resend.getOptions().getBaseUrl());
    }

    @Test
    public void testHttpClientUsesCustomBaseUrlAndUserAgent() {
        ResendOptions options = ResendOptions.builder()
                .baseUrl("https://example.test/")
                .userAgent("custom-agent/9")
                .connectTimeoutMs(1_000L)
                .readTimeoutMs(2_000L)
                .build();

        HttpClient client = new HttpClient(options);
        assertEquals("https://example.test", client.getBaseUrl());
        assertEquals("custom-agent/9", client.getUserAgent());
    }

    @Test
    public void testHttpClientDefaults() {
        HttpClient client = new HttpClient();
        assertEquals(HttpClient.BASE_API, client.getBaseUrl());
        assertEquals(HttpClient.USER_AGENT, client.getUserAgent());
    }

    @Test
    public void testServiceReceivesCustomOptions() {
        ResendOptions options = ResendOptions.builder()
                .baseUrl("https://mock.resend.test")
                .userAgent("sdk-test/1")
                .build();

        Emails emails = new Emails("re_test", options);
        assertTrue(emails.getHttpClient() instanceof HttpClient);
        HttpClient client = (HttpClient) emails.getHttpClient();
        assertEquals("https://mock.resend.test", client.getBaseUrl());
        assertEquals("sdk-test/1", client.getUserAgent());
    }

    @Test
    public void testBaseServiceDefaultConstructorStillWorks() {
        class Dummy extends BaseService {
            Dummy(String apiKey) {
                super(apiKey);
            }
        }
        Dummy dummy = new Dummy("re_test");
        assertNotNull(dummy.getHttpClient());
        assertTrue(dummy.getHttpClient() instanceof HttpClient);
        assertEquals(HttpClient.BASE_API, ((HttpClient) dummy.getHttpClient()).getBaseUrl());
    }
}
