package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.services.domains.model.ClaimDomainOptions;
import com.resend.services.domains.model.DomainClaimResponseSuccess;
import com.resend.services.util.DomainsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DomainClaimsTest {

    @Mock
    private DomainClaims domainClaims;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        domainClaims = mock(DomainClaims.class);
    }

    @Test
    public void testClaimDomain_Success() throws ResendException {
        DomainClaimResponseSuccess expected = DomainsUtil.claimDomainResponse();
        ClaimDomainOptions request = DomainsUtil.claimDomainRequest();

        when(domainClaims.create(request)).thenReturn(expected);

        DomainClaimResponseSuccess response = domainClaims.create(request);

        assertNotNull(response);
        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getName(), response.getName());
        assertEquals(expected.getStatus(), response.getStatus());
        assertEquals(expected.getDomainId(), response.getDomainId());
    }

    @Test
    public void testGetDomainClaim_Success() throws ResendException {
        DomainClaimResponseSuccess expected = DomainsUtil.claimDomainResponse();
        String domainId = expected.getDomainId();

        when(domainClaims.get(domainId)).thenReturn(expected);

        DomainClaimResponseSuccess response = domainClaims.get(domainId);

        assertNotNull(response);
        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getDomainId(), response.getDomainId());
        assertEquals(expected.getStatus(), response.getStatus());
    }

    @Test
    public void testVerifyDomainClaim_Success() throws ResendException {
        DomainClaimResponseSuccess expected = DomainsUtil.claimDomainResponse();
        String domainId = expected.getDomainId();

        when(domainClaims.verify(domainId)).thenReturn(expected);

        DomainClaimResponseSuccess response = domainClaims.verify(domainId);

        assertNotNull(response);
        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getDomainId(), response.getDomainId());
        assertEquals(expected.getStatus(), response.getStatus());
    }
}
