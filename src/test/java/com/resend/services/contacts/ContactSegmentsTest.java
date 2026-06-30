package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.contacts.model.*;
import com.resend.services.util.ContactsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ContactSegmentsTest {

    private static final String SEGMENT_ID = "78261eea-8f8b-4381-83c6-79fa7120f1cf";
    private static final String CONTACT_ID = "e169aa45-1ecf-4183-9955-b1499d5701d3";

    private static final String ADD_SEGMENT_JSON = "{\"id\":\"" + SEGMENT_ID + "\"}";
    private static final String REMOVE_SEGMENT_JSON = "{\"id\":\"" + SEGMENT_ID + "\",\"deleted\":true}";
    private static final String LIST_SEGMENTS_JSON =
            "{\"object\":\"list\",\"data\":[" +
            "{\"id\":\"" + SEGMENT_ID + "\",\"name\":\"Registered Users\",\"created_at\":\"2023-10-06T22:59:55.977Z\"}," +
            "{\"id\":\"b9d24c8e-bf1c-5d4d-cf1d-470cbd97482f\",\"name\":\"Premium Users\",\"created_at\":\"2023-11-12T14:23:10.123Z\"}" +
            "],\"has_more\":false}";

    @Mock
    private IHttpClient httpClient;

    private ContactSegments contactSegments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactSegments = new ContactSegments("test-api-key", httpClient);
    }

    @Test
    public void testAddContactToSegmentById_Success() throws ResendException {
        AddContactToSegmentOptions options = ContactsUtil.createAddContactToSegmentOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, ADD_SEGMENT_JSON, true);

        when(httpClient.perform(
                eq("/contacts/" + CONTACT_ID + "/segments/" + SEGMENT_ID),
                anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        AddContactToSegmentResponseSuccess res = contactSegments.add(options);

        assertNotNull(res);
        assertEquals(SEGMENT_ID, res.getId());
    }

    @Test
    public void testAddContactToSegmentByEmail_Success() throws ResendException {
        AddContactToSegmentOptions options = AddContactToSegmentOptions.builder()
                .email("steve.wozniak@gmail.com")
                .segmentId(SEGMENT_ID)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, ADD_SEGMENT_JSON, true);

        when(httpClient.perform(
                eq("/contacts/steve.wozniak@gmail.com/segments/" + SEGMENT_ID),
                anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        AddContactToSegmentResponseSuccess res = contactSegments.add(options);

        assertNotNull(res);
        assertEquals(SEGMENT_ID, res.getId());
    }

    @Test
    public void testAddContactToSegment_ApiError_ThrowsResendException() throws ResendException {
        AddContactToSegmentOptions options = ContactsUtil.createAddContactToSegmentOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Segment not found\"}", false);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> contactSegments.add(options));
        assertEquals(404, (int) ex.getStatusCode());
    }

    @Test
    public void testRemoveContactFromSegmentById_Success() throws ResendException {
        RemoveContactFromSegmentOptions options = ContactsUtil.createRemoveContactFromSegmentOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_SEGMENT_JSON, true);

        when(httpClient.perform(
                eq("/contacts/" + CONTACT_ID + "/segments/" + SEGMENT_ID),
                anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveContactFromSegmentResponseSuccess res = contactSegments.remove(options);

        assertNotNull(res);
        assertEquals(SEGMENT_ID, res.getId());
        assertEquals(Boolean.TRUE, res.getDeleted());
    }

    @Test
    public void testRemoveContactFromSegmentByEmail_Success() throws ResendException {
        RemoveContactFromSegmentOptions options = RemoveContactFromSegmentOptions.builder()
                .email("steve.wozniak@gmail.com")
                .segmentId(SEGMENT_ID)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_SEGMENT_JSON, true);

        when(httpClient.perform(
                eq("/contacts/steve.wozniak@gmail.com/segments/" + SEGMENT_ID),
                anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveContactFromSegmentResponseSuccess res = contactSegments.remove(options);

        assertNotNull(res);
        assertEquals(SEGMENT_ID, res.getId());
        assertEquals(Boolean.TRUE, res.getDeleted());
    }

    @Test
    public void testListContactSegmentsById_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_SEGMENTS_JSON, true);

        when(httpClient.perform(
                eq("/contacts/" + CONTACT_ID + "/segments"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactSegmentsResponseSuccess res = contactSegments.list(CONTACT_ID);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactSegmentsByEmail_Success() throws ResendException {
        String contactEmail = "steve.wozniak@gmail.com";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_SEGMENTS_JSON, true);

        when(httpClient.perform(
                eq("/contacts/" + contactEmail + "/segments"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactSegmentsResponseSuccess res = contactSegments.list(contactEmail);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactSegmentsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_SEGMENTS_JSON, true);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactSegmentsResponseSuccess res = contactSegments.list(CONTACT_ID, params);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
        assertEquals("list", res.getObject());
    }
}
