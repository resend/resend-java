package com.resend.services.util;

import com.resend.services.suppressions.model.*;

public class SuppressionsUtil {

    public static AddSuppressionOptions addSuppressionRequest() {
        return AddSuppressionOptions.builder()
                .email("steve.wozniak@example.com")
                .build();
    }

    public static AddSuppressionsOptions addSuppressionsRequest() {
        return AddSuppressionsOptions.builder()
                .email("steve.wozniak@example.com")
                .email("susan.kare@example.com")
                .build();
    }

    public static ListSuppressionsParams listSuppressionsParams() {
        return ListSuppressionsParams.builder()
                .limit(20)
                .origin(SuppressionOrigin.BOUNCE)
                .build();
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
}
