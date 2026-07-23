package com.resend.services.util;

import com.resend.services.suppressions.model.*;

import java.util.ArrayList;
import java.util.List;

public class SuppressionsUtil {

    public static AddSuppressionOptions addSuppressionRequest() {
        return AddSuppressionOptions.builder()
                .email("steve.wozniak@example.com")
                .build();
    }

    public static AddSuppressionResponseSuccess addSuppressionResponse() {
        return new AddSuppressionResponseSuccess("suppression", "e169aa45-1ecf-4183-9955-b1499d5701d3");
    }

    public static RemoveSuppressionResponseSuccess removeSuppressionResponse() {
        return new RemoveSuppressionResponseSuccess("suppression", "e169aa45-1ecf-4183-9955-b1499d5701d3", true);
    }

    public static AddSuppressionsOptions addSuppressionsRequest() {
        return AddSuppressionsOptions.builder()
                .email("steve.wozniak@example.com")
                .email("susan.kare@example.com")
                .build();
    }

    public static AddSuppressionsResponseSuccess addSuppressionsResponse() {
        List<AddedSuppression> data = new ArrayList<>();
        data.add(new AddedSuppression("suppression", "e169aa45-1ecf-4183-9955-b1499d5701d3"));
        data.add(new AddedSuppression("suppression", "520784e2-887d-4c25-b53c-4ad46ad38100"));
        return new AddSuppressionsResponseSuccess(data);
    }

    public static GetSuppressionResponseSuccess getSuppressionResponse() {
        return new GetSuppressionResponseSuccess(
                "suppression",
                "e169aa45-1ecf-4183-9955-b1499d5701d3",
                "steve.wozniak@example.com",
                "bounce",
                "4ef9a417-02e9-4d39-ad75-9611e0fcc33c",
                "2026-10-06T23:47:56.678Z");
    }

    public static ListSuppressionsParams listSuppressionsParams() {
        return ListSuppressionsParams.builder()
                .limit(20)
                .origin(SuppressionOrigin.BOUNCE)
                .build();
    }

    public static ListSuppressionsResponseSuccess listSuppressionsResponse() {
        List<Suppression> data = new ArrayList<>();
        data.add(new Suppression(
                "suppression",
                "e169aa45-1ecf-4183-9955-b1499d5701d3",
                "steve.wozniak@example.com",
                "manual",
                null,
                "2026-10-06T23:47:56.678Z"));
        data.add(new Suppression(
                "suppression",
                "520784e2-887d-4c25-b53c-4ad46ad38100",
                "susan.kare@example.com",
                "bounce",
                "4ef9a417-02e9-4d39-ad75-9611e0fcc33c",
                "2026-10-07T08:12:03.412Z"));
        return new ListSuppressionsResponseSuccess("list", false, data);
    }

    public static RemoveSuppressionsOptions removeSuppressionsByEmailsRequest() {
        return RemoveSuppressionsOptions.builder()
                .email("steve.wozniak@example.com")
                .email("susan.kare@example.com")
                .build();
    }

    public static RemoveSuppressionsOptions removeSuppressionsByIdsRequest() {
        return RemoveSuppressionsOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .build();
    }

    public static RemoveSuppressionsResponseSuccess removeSuppressionsResponse() {
        List<RemovedSuppression> data = new ArrayList<>();
        data.add(new RemovedSuppression("suppression", "e169aa45-1ecf-4183-9955-b1499d5701d3", true));
        return new RemoveSuppressionsResponseSuccess(data);
    }
}
