package com.resend.services.segments;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.segments.model.CreateSegmentOptions;
import com.resend.services.segments.model.CreateSegmentResponseSuccess;
import com.resend.services.segments.model.GetSegmentResponseSuccess;
import com.resend.services.segments.model.ListSegmentsResponseSuccess;
import com.resend.services.segments.model.RemoveSegmentResponseSuccess;

import com.resend.services.util.SegmentsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SegmentsTest {

    @Mock
    private Segments segments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        segments = mock(Segments.class);
    }

    @Test
    public void testCreateSegment_Success() throws ResendException {
        CreateSegmentResponseSuccess expectedSegment = SegmentsUtil.createSegmentResponse();
        CreateSegmentOptions param = SegmentsUtil.createSegmentRequest();

        when(segments.create(param))
                .thenReturn(expectedSegment);

        CreateSegmentResponseSuccess createdSeg = segments.create(param);

        assertEquals(createdSeg, expectedSegment);
        verify(segments, times(1)).create(param);
    }

    @Test
    public void testDeleteSegment_Success() throws ResendException {
        String segmentId = "123";
        RemoveSegmentResponseSuccess removed = SegmentsUtil.removeSegmentsResponseSuccess();
        when(segments.remove(segmentId))
                .thenReturn(removed);

        RemoveSegmentResponseSuccess res = segments.remove(segmentId);

        assertEquals(removed, res);
    }

    @Test
    public void testListSegments_Success() throws ResendException {
        ListSegmentsResponseSuccess expectedResponse = SegmentsUtil.createSegmentsListResponse();

        when(segments.list())
                .thenReturn(expectedResponse);

        ListSegmentsResponseSuccess res = segments.list();

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testListSegmentWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(3).build();
        ListSegmentsResponseSuccess expectedResponse = SegmentsUtil.createSegmentsListResponse();

        when(segments.list(params))
                .thenReturn(expectedResponse);

        ListSegmentsResponseSuccess res = segments.list(params);

        assertNotNull(res);
        assertEquals(params.getLimit(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testGetSegment_Success() throws ResendException {
        String segmentId = "123";
        GetSegmentResponseSuccess expected = SegmentsUtil.getSegmentResponseSuccess();

        when(segments.get(segmentId))
                .thenReturn(expected);

        GetSegmentResponseSuccess res = segments.get(segmentId);

        assertNotNull(res);
        assertEquals(expected.getId(), res.getId());
        assertEquals(expected.getName(), res.getName());
    }
}
