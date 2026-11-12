package com.resend.services.domains;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.domains.model.Domain;
import com.resend.services.domains.model.EnableForgeOptions;
import com.resend.services.domains.model.UpdateForgeOptions;
import com.resend.services.domains.model.VerifyForgeResponse;
import com.resend.services.util.DomainsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class DomainForgeTest {

    private static final String DOMAIN_ID = "d91cd9bd-1176-453e-8fc1-35364d380206";

    private static final String FORGE_DOMAIN_RESPONSE_JSON = "{" +
            "\"object\":\"domain\"," +
            "\"id\":\"" + DOMAIN_ID + "\"," +
            "\"name\":\"example.com\"," +
            "\"status\":\"pending\"," +
            "\"created_at\":\"2026-11-11 10:00:00.000000+00\"," +
            "\"region\":\"us-east-1\"," +
            "\"records\":[{\"record\":\"SPF\",\"name\":\"send\"," +
            "\"value\":\"v=spf1 include:spf.resend.com ~all\",\"type\":\"TXT\"," +
            "\"ttl\":\"Auto\",\"status\":\"pending\"}]," +
            "\"open_tracking\":false," +
            "\"click_tracking\":false" +
            "}";

    private static final String VERIFY_FORGE_RESPONSE_JSON =
            "{\"object\":\"domain\",\"id\":\"" + DOMAIN_ID + "\"}";

    @Mock
    private IHttpClient httpClient;

    private DomainForge domainForge;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        domainForge = new DomainForge("test-api-key", httpClient);
    }

    @Test
    public void testEnableForge_Success() throws Exception {
        EnableForgeOptions request = DomainsUtil.enableForgeRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, FORGE_DOMAIN_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Domain response = domainForge.enable(DOMAIN_ID, request);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertEquals("example.com", response.getName());

        verify(httpClient).perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.POST), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertEquals("send", body.get("return_path"));
    }

    @Test
    public void testEnableForge_WithoutReturnPath_SendsEmptyBody() throws Exception {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, FORGE_DOMAIN_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Domain response = domainForge.enable(DOMAIN_ID);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());

        verify(httpClient).perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.POST), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertTrue(body.isEmpty());
    }

    @Test
    public void testEnableForge_NullOptions_ThrowsResendException() {
        assertThrows(ResendException.class, () -> domainForge.enable(DOMAIN_ID, null));
    }

    @Test
    public void testEnableForge_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Forge is not available for this domain\"}", false);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> domainForge.enable(DOMAIN_ID));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testUpdateForge_Success() throws Exception {
        UpdateForgeOptions request = DomainsUtil.updateForgeRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, FORGE_DOMAIN_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Domain response = domainForge.update(DOMAIN_ID, request);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());

        verify(httpClient).perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.PATCH), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertEquals("outbound", body.get("return_path"));
    }

    @Test
    public void testUpdateForge_NullOptions_ThrowsResendException() {
        assertThrows(ResendException.class, () -> domainForge.update(DOMAIN_ID, null));
    }

    @Test
    public void testUpdateForge_ApiError_ThrowsResendException() throws ResendException {
        UpdateForgeOptions request = DomainsUtil.updateForgeRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"return_path_unavailable\",\"message\":\"The return path can no longer be changed\"}", false);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/forge"), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> domainForge.update(DOMAIN_ID, request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testVerifyForge_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, VERIFY_FORGE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/forge/verify"), anyString(), eq(HttpMethod.POST), eq(""), isNull()))
                .thenReturn(httpResponse);

        VerifyForgeResponse response = domainForge.verify(DOMAIN_ID);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertEquals("domain", response.getObject());
    }
}
