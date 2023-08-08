package com.resend.core.provider.impl;

public class PropertiesProvider {

    private String apiKey;

    private String apiUrl = "";

    public PropertiesProvider() {
        this.apiKey = System.getenv("RESEND_API_KEY");
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

}
