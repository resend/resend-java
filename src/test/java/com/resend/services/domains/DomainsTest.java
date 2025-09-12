package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.domains.dto.DomainDTO;
import com.resend.services.domains.model.*;
import com.resend.services.util.DomainsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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

    @Test
    public void testListDomains_Success() throws ResendException {
        ListDomainsResponse expectedResponse = DomainsUtil.createDomainListResponse();

        when(domains.list()).thenReturn(expectedResponse);


        ListDomainsResponse response = domains.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
    }

    @Test
    public void testListDomainsWithPagination_Success() throws ResendException {
        // Arrange
        ListParams params = ListParams.builder().limit(2).build();
        ListDomainsResponse expectedResponse = DomainsUtil.createDomainListResponse();
        List<DomainDTO> paginatedData = expectedResponse.getData().subList(0, params.getLimit());
        ListDomainsResponse paginatedResponse = new ListDomainsResponse(paginatedData, true);

        when(domains.list(params)).thenReturn(paginatedResponse);

        ListDomainsResponse response = domains.list(params);

        assertNotNull(response);
        assertEquals(params.getLimit(), response.getData().size());

    }
}
