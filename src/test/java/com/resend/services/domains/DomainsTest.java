package com.resend.services.domains;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.domains.model.*;
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
public class DomainsTest {

    private static final String DOMAIN_ID = "2c64b27c-6237-4626-85d2-a0a8b5832070";

    private static final String CREATE_RESPONSE_JSON =
            "{\"id\":\"" + DOMAIN_ID + "\",\"name\":\"resend.dev\"," +
            "\"created_at\":\"2023-04-08 00:11:13.110779+00\",\"status\":\"Active\"," +
            "\"region\":\"us-east-1\",\"dnsProvider\":\"DNSProviderXYZ\",\"records\":[]}";

    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"" + DOMAIN_ID + "\",\"name\":\"resend.dev\"," +
            "\"created_at\":\"2023-04-08 00:11:13.110779+00\",\"status\":\"Active\"," +
            "\"region\":\"us-east-1\",\"dnsProvider\":\"DNSProviderXYZ\",\"object\":\"domain\",\"records\":[]}";

    private static final String VERIFY_RESPONSE_JSON =
            "{\"object\":\"domain\",\"id\":\"" + DOMAIN_ID + "\"}";

    private static final String REMOVE_RESPONSE_JSON =
            "{\"object\":\"domain\",\"id\":\"" + DOMAIN_ID + "\",\"deleted\":true}";

    private static final String UPDATE_RESPONSE_JSON =
            "{\"id\":\"" + DOMAIN_ID + "\",\"object\":\"domain\"}";

    private static final String LIST_RESPONSE_JSON = "{\"data\":[" +
            "{\"id\":\"id-1\",\"name\":\"resend.dev\",\"created_at\":\"2023-04-08 00:11:13.11+00\"," +
            "\"status\":\"Active\",\"region\":\"us-east-1\"}," +
            "{\"id\":\"id-2\",\"name\":\"example.com\",\"created_at\":\"2023-01-01 12:00:00+00\"," +
            "\"status\":\"Active\",\"region\":\"us-east-2\"}," +
            "{\"id\":\"id-3\",\"name\":\"another.com\",\"created_at\":\"2023-02-15 08:30:00+00\"," +
            "\"status\":\"Inactive\",\"region\":\"us-west-1\"}" +
            "],\"has_more\":true,\"object\":\"list\"}";

    @Mock
    private IHttpClient httpClient;

    private Domains domains;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        domains = new Domains("test-api-key", httpClient);
    }

    @Test
    public void testCreateDomain_Success() throws ResendException {
        CreateDomainOptions request = DomainsUtil.createDomainRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateDomainResponse response = domains.create(request);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertEquals("resend.dev", response.getName());
        assertEquals("Active", response.getStatus());
    }

    @Test
    public void testCreateDomain_ApiError_ThrowsResendException() throws ResendException {
        CreateDomainOptions request = DomainsUtil.createDomainRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid domain\"}", false);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> domains.create(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testRetrieveDomain_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Domain response = domains.get(DOMAIN_ID);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertEquals("resend.dev", response.getName());
    }

    @Test
    public void testRetrieveDomain_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Domain not found\"}", false);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> domains.get(DOMAIN_ID));
    }

    @Test
    public void testVerifyDomain_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, VERIFY_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/verify"), anyString(), eq(HttpMethod.POST), eq(""), isNull()))
                .thenReturn(httpResponse);

        VerifyDomainResponse response = domains.verify(DOMAIN_ID);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertEquals("domain", response.getObject());
    }

    @Test
    public void testVerifyDomain_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Domain already verified\"}", false);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID + "/verify"), anyString(), eq(HttpMethod.POST), eq(""), isNull()))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> domains.verify(DOMAIN_ID));
    }

    @Test
    public void testDeleteDomain_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveDomainResponse response = domains.remove(DOMAIN_ID);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertTrue(response.isDeleted());
    }

    @Test
    public void testDeleteDomain_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Domain not found\"}", false);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> domains.remove(DOMAIN_ID));
    }

    @Test
    public void testUpdateDomain_Success() throws ResendException {
        UpdateDomainOptions request = DomainsUtil.updateDomainRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateDomainResponseSuccess response = domains.update(request);

        assertNotNull(response);
        assertEquals(DOMAIN_ID, response.getId());
        assertEquals("domain", response.getObject());
    }

    @Test
    public void testUpdateDomain_ApiError_ThrowsResendException() throws ResendException {
        UpdateDomainOptions request = DomainsUtil.updateDomainRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid update\"}", false);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> domains.update(request));
    }

    @Test
    public void testListDomains_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListDomainsResponse response = domains.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals("id-1", response.getData().get(0).getId());
    }

    @Test
    public void testListDomains_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(401,
                "{\"name\":\"unauthorized\",\"message\":\"Unauthorized\"}", false);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> domains.list());
    }

    @Test
    public void testListDomainsWithPagination_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains?limit=2"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        com.resend.core.net.ListParams params = com.resend.core.net.ListParams.builder().limit(2).build();
        ListDomainsResponse response = domains.list(params);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testCreateDomainWithTrackingTlsAndCapabilities_SendsFields() throws Exception {
        CreateDomainOptions request = CreateDomainOptions.builder()
                .name("resend.dev")
                .openTracking(true)
                .clickTracking(false)
                .tls(Tls.ENFORCED)
                .capabilities(DomainCapabilities.builder()
                        .sending(DomainCapabilityStatus.ENABLED)
                        .receiving(DomainCapabilityStatus.DISABLED)
                        .build())
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        domains.create(request);

        verify(httpClient).perform(eq("/domains"), anyString(), eq(HttpMethod.POST), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertEquals(true, body.get("open_tracking"));
        assertEquals(false, body.get("click_tracking"));
        assertEquals("enforced", body.get("tls"));
        Map<String, Object> capabilities = (Map<String, Object>) body.get("capabilities");
        assertEquals("enabled", capabilities.get("sending"));
        assertEquals("disabled", capabilities.get("receiving"));
    }

    @Test
    public void testCreateDomainWithoutOptionalFields_OmitsFields() throws Exception {
        CreateDomainOptions request = CreateDomainOptions.builder()
                .name("resend.dev")
                .capabilities(DomainCapabilities.builder()
                        .receiving(DomainCapabilityStatus.ENABLED)
                        .build())
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        domains.create(request);

        verify(httpClient).perform(eq("/domains"), anyString(), eq(HttpMethod.POST), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertFalse(body.containsKey("open_tracking"));
        assertFalse(body.containsKey("click_tracking"));
        assertFalse(body.containsKey("tls"));
        Map<String, Object> capabilities = (Map<String, Object>) body.get("capabilities");
        assertFalse(capabilities.containsKey("sending"));
        assertEquals("enabled", capabilities.get("receiving"));
    }

    @Test
    public void testCreateDomainWithoutCapabilities_OmitsField() throws Exception {
        CreateDomainOptions request = CreateDomainOptions.builder()
                .name("resend.dev")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        domains.create(request);

        verify(httpClient).perform(eq("/domains"), anyString(), eq(HttpMethod.POST), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertFalse(body.containsKey("capabilities"));
    }

    @Test
    public void testUpdateDomainTls_SendsLowercaseValue() throws Exception {
        UpdateDomainOptions request = UpdateDomainOptions.builder()
                .id(DOMAIN_ID)
                .tls(Tls.OPPORTUNISTIC)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);
        ArgumentCaptor<String> payload = ArgumentCaptor.forClass(String.class);

        when(httpClient.perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        domains.update(request);

        verify(httpClient).perform(eq("/domains/" + DOMAIN_ID), anyString(), eq(HttpMethod.PATCH), payload.capture(), any(MediaType.class));
        Map<String, Object> body = new ObjectMapper().readValue(payload.getValue(), Map.class);
        assertEquals("opportunistic", body.get("tls"));
    }
}
