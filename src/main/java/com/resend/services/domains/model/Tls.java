package com.resend.services.domains.model;

public enum Tls {
    ENFORCED("enforced"),
    OPPORTUNISTIC("opportunistic");

    private final String value;

    Tls(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
