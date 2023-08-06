package com.resend;

import com.resend.core.modules.Emails;
import com.resend.core.modules.EmailsImpl;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.impl.HttpClient;

public class Resend {
    private final Emails emails;
    private final IHttpClient httpClient;

    public Resend(final String apiKey) {
        this.httpClient = new HttpClient(apiKey);
        this.emails = new EmailsImpl(this.httpClient);
    }

    public Emails emails() {
        return emails;
    }
}