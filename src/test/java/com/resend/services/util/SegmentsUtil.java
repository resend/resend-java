package com.resend.services.util;

import com.resend.services.segments.model.*;

import java.util.ArrayList;
import java.util.List;

public class SegmentsUtil {
    public static CreateSegmentOptions createSegmentRequest() {
        return CreateSegmentOptions.builder()
                .name("seg")
                .build();
    }
    public static CreateSegmentResponseSuccess createSegmentResponse() {
        return new CreateSegmentResponseSuccess("123", "seg", "audience");
    }

    public static RemoveSegmentResponseSuccess removeSegmentsResponseSuccess() {
        return new RemoveSegmentResponseSuccess("123", "audience", true);
    }

    public static ListSegmentsResponseSuccess createSegmentsListResponse() {
        List<Segment> segList = new ArrayList<>();

        Segment seg1 = new Segment("1", "test1", "2023-04-08T00:11:13.110779+00:00");
        Segment seg2 = new Segment("2", "test2", "2023-04-08T00:11:13.110779+00:00");
        Segment seg3 = new Segment("3", "test3", "2023-04-08T00:11:13.110779+00:00");

        segList.add(seg1);
        segList.add(seg2);
        segList.add(seg3);

        return new ListSegmentsResponseSuccess(segList, "list", true);
    }

    public static GetSegmentResponseSuccess getSegmentResponseSuccess() {
        return new GetSegmentResponseSuccess("123", "seg", "2023-04-08T00:11:13.110779+00:00", "audience");
    }
}
