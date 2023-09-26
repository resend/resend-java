package com.resend;

import com.resend.core.exception.ResendException;
import com.resend.services.apikeys.model.ApiKeyItem;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.CreateApiKeyRequest;
import com.resend.services.apikeys.model.ListApiKeysResponse;

public class Main {

    public static void main(String[] args) {
        Resend resend = new Resend("re_ExqkPJNi_DYsCeH2qY5Ji8JhpAujVR5vo");

        CreateApiKeyRequest params = CreateApiKeyRequest.builder()
                .name("java1")
                .permission("full_access").build();

        try {
            CreateApiKeyResponse response = resend.apiKeys().create(params);
            System.out.println(response.getId());
            ListApiKeysResponse list = resend.apiKeys().list();
            for (ApiKeyItem apikey: list.getData()
                 ) {
                System.out.println(apikey.getName());

            }

            System.out.println(resend.apiKeys().remove(response.getId()));
        } catch (ResendException e) {
            throw new RuntimeException(e);
        }
    }
}
