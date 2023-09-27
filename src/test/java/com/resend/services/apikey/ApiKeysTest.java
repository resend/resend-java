package com.resend.services.apikey;

import com.resend.core.exception.ResendException;
import com.resend.services.apikeys.ApiKeys;
import com.resend.services.apikeys.model.CreateApiKeyRequest;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.ListApiKeysResponse;
import com.resend.services.util.ApiKeysUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ApiKeysTest {

    @Mock
    private ApiKeys apiKeys;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        apiKeys = mock(ApiKeys.class);
    }

    @Test
    public void testCreateApiKey_Success() throws ResendException {
        CreateApiKeyResponse expectedApiKey = ApiKeysUtil.createApiKeyResponse();
        CreateApiKeyRequest param = ApiKeysUtil.createApiKeyRequest();

        when(apiKeys.create(param))
                .thenReturn(expectedApiKey);

        CreateApiKeyResponse retrievedApiKey = apiKeys.create(param);


        assertEquals(retrievedApiKey, expectedApiKey);
        verify(apiKeys, times(1)).create(param);
    }

    @Test
    public void testDeleteApiKey_Success() throws ResendException {
        String apiKeyId = "123";
        when(apiKeys.remove(apiKeyId))
                .thenReturn(true);

        boolean response = apiKeys.remove(apiKeyId);

        assertEquals(true, response);
    }

    @Test
    public void testListApiKeys_Success() throws ResendException {
        ListApiKeysResponse expectedResponse = ApiKeysUtil.createListApiKeyResponse();

        when(apiKeys.list())
                .thenReturn(expectedResponse);

        ListApiKeysResponse response = apiKeys.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
    }
}
