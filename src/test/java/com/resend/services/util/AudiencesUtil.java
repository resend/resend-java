package com.resend.services.util;

import com.resend.services.audiences.model.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class AudiencesUtil {
    public static CreateAudienceOptions createAudienceRequest() {
        return CreateAudienceOptions.builder()
                .name("aud")
                .build();
    }
    public static CreateAudienceResponseSuccess createAudienceResponse() {
        return new CreateAudienceResponseSuccess("123", "aud", "audience");
    }

    public static RemoveAudienceResponseSuccess removeAudiencesResponseSuccess() {
        return new RemoveAudienceResponseSuccess("123", "audience", true);
    }

    public static ListAudiencesResponseSuccess createAudiencesListResponse() {
        List<Audience> audList = new ArrayList<>();

        Audience aud1 = new Audience("1", "test1", OffsetDateTime.now());
        Audience aud2 = new Audience("2", "test2", OffsetDateTime.now());
        Audience aud3 = new Audience("3", "test3", OffsetDateTime.now());

        audList.add(aud1);
        audList.add(aud2);
        audList.add(aud3);

        return new ListAudiencesResponseSuccess(audList, "list");
    }
}