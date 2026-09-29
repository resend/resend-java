package com.resend.core.net.impl;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

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
    public void testConstructor_RejectsNullOkHttpClient() {
        assertThrows(IllegalArgumentException.class, () -> new HttpClient(null));
    }
}
