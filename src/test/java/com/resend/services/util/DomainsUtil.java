package com.resend.services.util;

import com.resend.services.domains.dto.DomainDTO;
import com.resend.services.domains.model.*;
import java.util.ArrayList;
import java.util.List;

public class DomainsUtil {

    public static final CreateDomainOptions createDomainRequest() {
        return CreateDomainOptions.builder()
                .name("resend.dev")
                .customReturnPath("custom")
                .build();
    }

    public static ListDomainsResponse createDomainListResponse() {
        List<DomainDTO> data = new ArrayList<>();

        DomainDTO domain1 = new DomainDTO(
                "id-1",
                "resend.dev",
                "2023-04-08T00:11:13.110Z",
                "Active",
                "us-east-1"
        );

        DomainDTO domain2 = new DomainDTO(
                "id-2",
                "example.com",
                "2023-01-01T12:00:00.000Z",
                "Active",
                "us-east-2"
        );

        DomainDTO domain3 = new DomainDTO(
                "id-3",
                "another.com",
                "2023-02-15T08:30:00.000Z",
                "Inactive",
                "us-west-1"
        );

        data.add(domain1);
        data.add(domain2);
        data.add(domain3);

        return new ListDomainsResponse(data, true, "list");
    }

    public static final CreateDomainResponse createDomainResponse() {
        return new CreateDomainResponse(
                "2c64b27c-6237-4626-85d2-a0a8b5832070",
                "resend.dev",
                "2023-04-08T00:11:13.110779+00:00",
                "Active",
                "us-east-1",
                "DNSProviderXYZ",
                new ArrayList<>()
        );
    }

    public static final Domain createDomain() {
        return new Domain("2c64b27c-6237-4626-85d2-a0a8b5832070",
                "resend.dev",
                "2023-04-08T00:11:13.110779+00:00",
                "Active",
                "us-east-1",
                "DNSProviderXYZ",
                "domain",
                new ArrayList<>());
    }

    public static final VerifyDomainResponse verifyDomain() {
        return new VerifyDomainResponse(
                "domain",
                "2c64b27c-6237-4626-85d2-a0a8b5832070");
    }

    public static final RemoveDomainResponse deleteDomain() {
        return new RemoveDomainResponse(
                "domain",
                "2c64b27c-6237-4626-85d2-a0a8b5832070",
                true);
    }

    public static final UpdateDomainResponseSuccess updateDomain() {
        return new UpdateDomainResponseSuccess(
                "2c64b27c-6237-4626-85d2-a0a8b5832070",
                "domain");
    }

    public static final UpdateDomainOptions updateDomainRequest() {
        return UpdateDomainOptions.builder()
                .openTracking(true)
                .clickTracking(true)
                .tls(Tls.OPPORTUNISTIC)
                .id("2c64b27c-6237-4626-85d2-a0a8b5832070")
                .build();
    }

}
