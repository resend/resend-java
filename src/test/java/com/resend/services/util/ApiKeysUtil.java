package com.resend.services.util;

import com.resend.services.apikeys.model.ApiKeyItem;
import com.resend.services.apikeys.model.CreateApiKeyRequest;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.ListApiKeysResponse;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiKeysUtil {

    public static final CreateApiKeyRequest createApiKeyRequest() {
        return CreateApiKeyRequest.builder()
                .name("prod")
                .permission("full_access")
                .build();
    }

    public static final CreateApiKeyResponse createApiKeyResponse() {
        return new CreateApiKeyResponse("123", "re_123");
    }

    public static final ListApiKeysResponse createListApiKeyResponse() {
        List<ApiKeyItem> apiKeys = new ArrayList<>();

        // Create some example API key items
        ApiKeyItem apiKeyItem1 = new ApiKeyItem(
                "abcdefg-4321-5678-ijklmnop",
                "Production",
                OffsetDateTime.parse("2023-04-08T00:11:13.110779+00:00"));

        ApiKeyItem apiKeyItem2 = new ApiKeyItem(
                "abcdefg-1234-5678-ijklmnop",
                "Staging",
                OffsetDateTime.parse("2023-04-08T00:11:13.110779+00:00"));

        apiKeys.add(apiKeyItem1);
        apiKeys.add(apiKeyItem2);

        ListApiKeysResponse response = new ListApiKeysResponse(apiKeys);

        return response;
    }

}
