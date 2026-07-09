package com.resend.services.util;

import com.resend.services.oauthgrants.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OAuthGrantsUtil {

    public static ListOAuthGrantsResponseSuccess listOAuthGrantsResponse() {
        List<OAuthGrant> grants = new ArrayList<>();

        OAuthGrantClient client = new OAuthGrantClient("Resend CLI", "https://example.com/logo.png");

        OAuthGrant grant1 = new OAuthGrant(
                "650e8400-e29b-41d4-a716-446655440001",
                "430eed87-632a-4ea6-90db-0aace67ec228",
                Collections.singletonList("emails:send"),
                null,
                "2026-04-08 00:11:13.110779+00",
                null,
                null,
                client
        );

        OAuthGrant grant2 = new OAuthGrant(
                "650e8400-e29b-41d4-a716-446655440002",
                "430eed87-632a-4ea6-90db-0aace67ec228",
                java.util.Arrays.asList("emails:send", "domains:read"),
                "https://api.resend.com",
                "2026-04-07 00:11:13.110779+00",
                "2026-04-09 00:11:13.110779+00",
                "revoked_from_api",
                client
        );

        grants.add(grant1);
        grants.add(grant2);

        return new ListOAuthGrantsResponseSuccess("list", false, grants);
    }

    public static RemoveOAuthGrantResponseSuccess removeOAuthGrantResponse() {
        return new RemoveOAuthGrantResponseSuccess(
                "oauth_grant",
                "650e8400-e29b-41d4-a716-446655440001",
                "2026-04-08T00:11:13.110Z",
                "revoked_from_api"
        );
    }
}
