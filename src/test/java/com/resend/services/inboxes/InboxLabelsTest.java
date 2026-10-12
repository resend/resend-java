package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.inboxes.model.*;
import com.resend.services.util.InboxesUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.resend.services.util.InboxesUtil.INBOX_ID;
import static com.resend.services.util.InboxesUtil.LABEL_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class InboxLabelsTest {

    private static final String LABELS_PATH = "/inboxes/" + INBOX_ID + "/labels";

    @Mock
    private IHttpClient httpClient;

    private InboxLabels labels;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        labels = new Inboxes("test-api-key", httpClient).labels();
    }

    @Test
    public void testListLabels_Success() throws ResendException {
        String json = "{\"object\":\"list\",\"data\":[{\"id\":\"" + LABEL_ID + "\",\"name\":\"Urgent\"," +
                "\"color\":\"#E93D82\",\"created_at\":\"2026-08-05T14:07:42.881Z\"}]}";
        when(httpClient.perform(eq(LABELS_PATH), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, json, true));

        ListInboxLabelsResponseSuccess response = labels.list(INBOX_ID);

        assertEquals("list", response.getObject());
        InboxLabel label = response.getData().get(0);
        assertEquals(LABEL_ID, label.getId());
        assertEquals("Urgent", label.getName());
        assertEquals("#E93D82", label.getColor());
        assertEquals("2026-08-05T14:07:42.881Z", label.getCreatedAt());
    }

    @Test
    public void testCreateLabel_Success() throws ResendException {
        when(httpClient.perform(eq(LABELS_PATH), anyString(), eq(HttpMethod.POST),
                eq("{\"name\":\"Urgent\",\"color\":\"#E93D82\"}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(201, "{\"object\":\"inbox_label\",\"id\":\"" + LABEL_ID + "\"}", true));

        CreateInboxLabelResponseSuccess response = labels.create(INBOX_ID, InboxesUtil.createLabelRequest());

        assertEquals("inbox_label", response.getObject());
        assertEquals(LABEL_ID, response.getId());
    }

    @Test
    public void testCreateLabel_Duplicate_ThrowsResendException() throws ResendException {
        when(httpClient.perform(eq(LABELS_PATH), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(422, "{\"name\":\"validation_error\",\"message\":\"Duplicate label\"}", false));

        ResendException ex = assertThrows(ResendException.class, () -> labels.create(INBOX_ID, InboxesUtil.createLabelRequest()));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testUpdateLabel_Success() throws ResendException {
        when(httpClient.perform(eq(LABELS_PATH + "/" + LABEL_ID), anyString(), eq(HttpMethod.PATCH),
                eq("{\"color\":\"#F76B15\"}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"inbox_label\",\"id\":\"" + LABEL_ID + "\"}", true));

        UpdateInboxLabelResponseSuccess response = labels.update(INBOX_ID, LABEL_ID, InboxesUtil.updateLabelRequest());

        assertEquals(LABEL_ID, response.getId());
    }

    @Test
    public void testRemoveLabel_Success() throws ResendException {
        when(httpClient.perform(eq(LABELS_PATH + "/" + LABEL_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"inbox_label\",\"id\":\"" + LABEL_ID + "\",\"deleted\":true}", true));

        RemoveInboxLabelResponseSuccess response = labels.remove(INBOX_ID, LABEL_ID);

        assertEquals(LABEL_ID, response.getId());
        assertTrue(response.getDeleted());
    }
}
