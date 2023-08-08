package com.resend.core.provider.impl;

import com.resend.core.provider.AuthenticationProvider;

public class AuthenticationProviderStandard extends AuthenticationProvider {
    private String token;

    public AuthenticationProviderStandard(final String token) {
        this.token = token;
    }

    @Override
    public String token() {
        return this.token;
    }

    @Override
    public String baseUrl() {
        return super.getPropertiesProvider().getApiUrl();
    }

}
