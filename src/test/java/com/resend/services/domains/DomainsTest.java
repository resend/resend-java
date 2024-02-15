package com.resend.services.domains;

import com.resend.core.exception.ResendException;
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
    public void testCreateDomain_Success() throws ResendException {
        CreateDomainResponse expectedDomain = DomainsUtil.createDomainResponse();

        CreateDomainOptions request = DomainsUtil.createDomainRequest();
        when(domains.create(request))
                .thenReturn(expectedDomain);

        CreateDomainResponse response = domains.create(request);

        assertNotNull(response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testRetrieveDomain_Success() throws ResendException {
        Domain expectedDomain = DomainsUtil.createDomain();

        when(domains.get(expectedDomain.getId()))
                .thenReturn(expectedDomain);

        Domain response = domains.get(expectedDomain.getId());

        assertNotNull(response);
        assertEquals(expectedDomain, response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testVerifyDomain_Success() throws ResendException {
        VerifyDomainResponse expectedResponse = DomainsUtil.verifyDomain();

        when(domains.verify(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        VerifyDomainResponse response = domains.verify(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }

    @Test
    public void testDeleteDomain_Success() throws ResendException {
        RemoveDomainResponse expectedResponse = DomainsUtil.deleteDomain();

        when(domains.remove(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        RemoveDomainResponse response = domains.remove(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }

    @Test
    public void testUpdateDomain_Success() throws ResendException {
        UpdateDomainResponseSuccess expectedResponse = DomainsUtil.updateDomain();
        UpdateDomainOptions params = DomainsUtil.updateDomainRequest();

        when(domains.update(params))
                .thenReturn(expectedResponse);

        UpdateDomainResponseSuccess response = domains.update(params);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }
}
