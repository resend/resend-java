package com.resend.services.util;

import com.resend.services.domains.model.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class DomainsUtil {

    public static final CreateDomainOptions createDomainRequest() {
        return CreateDomainOptions.builder()
                .name("resend.dev")
                .build();
    }

    public static final CreateDomainResponse createDomainResponse() {
        return new CreateDomainResponse(
                "2c64b27c-6237-4626-85d2-a0a8b5832070",
                "resend.dev",
                OffsetDateTime.now(), // Replace with the actual creation date/time
                "Active", // Replace with the desired status
                "us-east-1", // Replace with the desired region
                "DNSProviderXYZ", // Replace with the desired DNS provider
                new ArrayList<>() // An empty list of records or add your records here
        );
    }

    public static final Domain createDomain() {
        return new Domain("2c64b27c-6237-4626-85d2-a0a8b5832070",
                "resend.dev",
                OffsetDateTime.now(),
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

}
