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
        when(resendDomains.create(request))
                .thenReturn(expectedDomain);

        CreateDomainResponse response = resendDomains.create(request);

        assertNotNull(response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testRetrieveDomain_Success() {
        Domain expectedDomain = ResendDomainsUtil.createDomain();

        when(resendDomains.get(expectedDomain.getId()))
                .thenReturn(expectedDomain);

        Domain response = resendDomains.get(expectedDomain.getId());

        assertNotNull(response);
        assertEquals(expectedDomain, response);
        assertEquals(expectedDomain.getId(), response.getId());
    }

    @Test
    public void testVerifyDomain_Success() {
        VerifyDomainResponse expectedResponse = ResendDomainsUtil.verifyDomain();

        when(resendDomains.verify(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        VerifyDomainResponse response = resendDomains.verify(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }

    @Test
    public void testDeleteDomain_Success() {
        DeleteDomainResponse expectedResponse = ResendDomainsUtil.deleteDomain();

        when(resendDomains.delete(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        DeleteDomainResponse response = resendDomains.delete(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
    }
}
