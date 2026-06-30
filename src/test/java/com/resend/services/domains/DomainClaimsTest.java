package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.domains.model.ClaimDomainOptions;
import com.resend.services.domains.model.DomainClaimResponseSuccess;
import com.resend.services.util.DomainsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class DomainClaimsTest {

    private static final String CLAIM_RESPONSE_JSON = "{" +
            "\"object\":\"domain_claim\"," +
            "\"id\":\"dacf4072-4119-4d88-932f-6c6126d3a9d1\"," +
            "\"name\":\"example.com\"," +
            "\"status\":\"pending\"," +
            "\"domain_id\":\"d91cd9bd-1176-453e-8fc1-35364d380206\"," +
            "\"region\":\"us-east-1\"," +
            "\"record\":{\"type\":\"TXT\",\"name\":\"example.com\"," +
            "\"value\":\"resend-domain-verification=3f8a1c2d4e5b6a7f8091a2b3c4d5e6f7\",\"ttl\":\"Auto\"}," +
            "\"created_at\":\"2026-06-16 17:12:02.059593+00\"," +
            "\"expires_at\":\"2026-06-23 17:12:02.059593+00\"" +
            "}";

    @Mock
    private IHttpClient httpClient;

    private DomainClaims domainClaims;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        domainClaims = new DomainClaims("test-api-key", httpClient);
    }

    @Test
    public void testClaimDomain_Success() throws ResendException {
        ClaimDomainOptions request = DomainsUtil.claimDomainRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CLAIM_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/claim"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        DomainClaimResponseSuccess response = domainClaims.create(request);

        assertNotNull(response);
        assertEquals("dacf4072-4119-4d88-932f-6c6126d3a9d1", response.getId());
        assertEquals("example.com", response.getName());
        assertEquals("pending", response.getStatus());
        assertEquals("d91cd9bd-1176-453e-8fc1-35364d380206", response.getDomainId());
    }

    @Test
    public void testClaimDomain_NullOptions_ThrowsResendException() {
        assertThrows(ResendException.class, () -> domainClaims.create(null));
    }

    @Test
    public void testClaimDomain_ApiError_ThrowsResendException() throws ResendException {
        ClaimDomainOptions request = DomainsUtil.claimDomainRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid domain name\"}", false);

        when(httpClient.perform(eq("/domains/claim"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> domainClaims.create(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetDomainClaim_Success() throws ResendException {
        String domainId = "d91cd9bd-1176-453e-8fc1-35364d380206";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CLAIM_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + domainId + "/claim"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        DomainClaimResponseSuccess response = domainClaims.get(domainId);

        assertNotNull(response);
        assertEquals("dacf4072-4119-4d88-932f-6c6126d3a9d1", response.getId());
        assertEquals("d91cd9bd-1176-453e-8fc1-35364d380206", response.getDomainId());
        assertEquals("pending", response.getStatus());
    }

    @Test
    public void testVerifyDomainClaim_Success() throws ResendException {
        String domainId = "d91cd9bd-1176-453e-8fc1-35364d380206";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CLAIM_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/domains/" + domainId + "/claim/verify"), anyString(), eq(HttpMethod.POST), eq(""), isNull()))
                .thenReturn(httpResponse);

        DomainClaimResponseSuccess response = domainClaims.verify(domainId);

        assertNotNull(response);
        assertEquals("dacf4072-4119-4d88-932f-6c6126d3a9d1", response.getId());
        assertEquals("d91cd9bd-1176-453e-8fc1-35364d380206", response.getDomainId());
        assertEquals("pending", response.getStatus());
    }
}
