package com.resend.services.domains;

import com.resend.services.domains.model.*;
import com.resend.services.util.ResendDomainsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResendDomainsTest {

    @Mock
    private ResendDomains resendDomains;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resendDomains = mock(ResendDomains.class);
    }

    @Test
    public void testCreateDomain_Success() {
        CreateDomainResponse expectedDomain = ResendDomainsUtil.createDomainResponse();

        CreateDomainRequest request = ResendDomainsUtil.createDomainRequest();
        when(resendDomains.createDomain(request))
                .thenReturn(expectedDomain);

        CreateDomainResponse response = resendDomains.createDomain(request);

        assertNotNull(response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testRetrieveDomain_Success() {
        Domain expectedDomain = ResendDomainsUtil.createDomain();

        when(resendDomains.retrieveDomain(expectedDomain.getId()))
                .thenReturn(expectedDomain);

        Domain response = resendDomains.retrieveDomain(expectedDomain.getId());

        assertNotNull(response);
        assertEquals(expectedDomain, response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testVerifyDomain_Success() {
        VerifyDomainResponse expectedResponse = ResendDomainsUtil.verifyDomain();

        when(resendDomains.verifyDomain(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        VerifyDomainResponse response = resendDomains.verifyDomain(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }

    @Test
    public void testDeleteDomain_Success() {
        DeleteDomainResponse expectedResponse = ResendDomainsUtil.deleteDomain();

        when(resendDomains.deleteDomain(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        DeleteDomainResponse response = resendDomains.deleteDomain(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }
}
