package com.resend.core.provider.impl;

import com.resend.core.provider.AuthenticationProvider;

public class AuthenticationProviderDefault extends AuthenticationProvider {

    @Override
    public String token() {
        return super.getPropertiesProvider().getApiKey();
    }

    @Override
    public String baseUrl() {
        return super.getPropertiesProvider().getApiUrl();
    }

}
