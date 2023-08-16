package com.resend.core.service;

import com.resend.core.mapper.ResendMapper;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.impl.HttpClient;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderDefault;

public abstract class BaseService {

    private AuthenticationProvider authenticationProvider;
    protected final IHttpClient httpClient;
    protected final ResendMapper resendMapper;

    public BaseService() {
        this.authenticationProvider = new AuthenticationProviderDefault();
        this.httpClient = new HttpClient();
        this.resendMapper = new ResendMapper();
    }

    public BaseService(final AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.httpClient = new HttpClient();
        this.resendMapper = new ResendMapper();
    }

    public IHttpClient getHttpClient() {
        return httpClient;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }
}