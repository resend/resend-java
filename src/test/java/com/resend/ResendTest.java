package com.resend;

import com.resend.services.emails.Emails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
