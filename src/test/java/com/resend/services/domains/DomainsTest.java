package com.resend.services.domains;

import com.resend.services.domains.model.*;
import com.resend.services.util.DomainsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DomainsTest {

    @Mock
    private Domains domains;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        domains = mock(Domains.class);
    }

    @Test
    public void testCreateDomain_Success() {
        CreateDomainResponse expectedDomain = DomainsUtil.createDomainResponse();

        CreateDomainRequest request = DomainsUtil.createDomainRequest();
        when(domains.createDomain(request))
                .thenReturn(expectedDomain);

        CreateDomainResponse response = domains.createDomain(request);

        assertNotNull(response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testRetrieveDomain_Success() {
        Domain expectedDomain = DomainsUtil.createDomain();

        when(domains.retrieveDomain(expectedDomain.getId()))
                .thenReturn(expectedDomain);

        Domain response = domains.retrieveDomain(expectedDomain.getId());

        assertNotNull(response);
        assertEquals(expectedDomain, response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testVerifyDomain_Success() {
        VerifyDomainResponse expectedResponse = DomainsUtil.verifyDomain();

        when(domains.verifyDomain(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        VerifyDomainResponse response = domains.verifyDomain(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }

    @Test
    public void testDeleteDomain_Success() {
        DeleteDomainResponse expectedResponse = DomainsUtil.deleteDomain();

        when(domains.deleteDomain(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        DeleteDomainResponse response = domains.deleteDomain(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }
}
