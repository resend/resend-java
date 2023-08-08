package com.resend.core.provider;

import com.resend.core.provider.impl.PropertiesProvider;

public abstract class AuthenticationProvider {
    private PropertiesProvider propertiesProvider;

    public AuthenticationProvider() {
        this.propertiesProvider = new PropertiesProvider();
    }

    public abstract String token();

    public abstract String baseUrl();

    public PropertiesProvider getPropertiesProvider() {
        return propertiesProvider;
    }

    public void setPropertiesProvider(PropertiesProvider propertiesProvider) {
        this.propertiesProvider = propertiesProvider;
    }

}