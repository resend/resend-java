package com.resend.services.util;

import com.resend.services.apikeys.model.ApiKey;
import com.resend.services.apikeys.model.CreateApiKeyOptions;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.ListApiKeysResponse;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiKeysUtil {

    public static final CreateApiKeyOptions createApiKeyRequest() {
        return CreateApiKeyOptions.builder()
                .name("prod")
                .permission("full_access")
                .build();
    }

    public static final CreateApiKeyResponse createApiKeyResponse() {
        return new CreateApiKeyResponse("123", "re_123");
    }

    public static final ListApiKeysResponse createListApiKeyResponse() {
        List<ApiKey> apiKeys = new ArrayList<>();

        // Create some example API key items
        ApiKey apiKey1 = new ApiKey(
                "abcdefg-4321-5678-ijklmnop",
                "Production",
                OffsetDateTime.parse("2023-04-08T00:11:13.110779+00:00"));

        ApiKey apiKey2 = new ApiKey(
                "abcdefg-1234-5678-ijklmnop",
                "Staging",
                OffsetDateTime.parse("2023-04-08T00:11:13.110779+00:00"));

        apiKeys.add(apiKey1);
        apiKeys.add(apiKey2);

        ListApiKeysResponse response = new ListApiKeysResponse(apiKeys);

        return response;
    }

}
