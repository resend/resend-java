package com.resend;

import com.resend.core.net.IHttpClient;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderDefault;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ResendTest {

    @Mock
    private AuthenticationProvider authenticationProvider;

    private Resend resend;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resend = new Resend(authenticationProvider);
    }

    @Test
    public void testGetHttpClient() {
        IHttpClient result = resend.getHttpClient();
        assertEquals(resend.httpClient, result);
    }

    @Test
    public void testGetAuthenticationProvider() {
        AuthenticationProvider result = resend.getAuthenticationProvider();
        assertEquals(authenticationProvider, result);
    }

    @Test
    public void testDefaultConstructor() {
        Resend defaultResend = new Resend();

        assertNotNull(defaultResend.getHttpClient());
        assertNotNull(defaultResend.resendMapper);

        assertTrue(defaultResend.getAuthenticationProvider() instanceof AuthenticationProviderDefault);
    }

    @Test
    public void testConstructorWithAuthenticationProvider() {
        assertNotNull(resend.getHttpClient());
        assertNotNull(resend.resendMapper);
        assertEquals(authenticationProvider, resend.getAuthenticationProvider());
    }
}
