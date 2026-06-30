package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.contacts.model.ListContactTopicsResponse;
import com.resend.services.contacts.model.UpdateContactTopicsOptions;
import com.resend.services.contacts.model.UpdateContactTopicsResponse;
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
public class ContactTopicsTest {

    private static final String CONTACT_ID = "e169aa45-1ecf-4183-9955-b1499d5701d3";

    private static final String LIST_TOPICS_JSON =
            "{\"object\":\"list\",\"data\":[" +
            "{\"id\":\"b6d24b8e-af0b-4c3c-be0c-359bbd97381e\",\"name\":\"Product Updates\"," +
            "\"description\":\"New features, and latest announcements.\",\"subscription\":\"opt_in\"}," +
            "{\"id\":\"07d84122-7224-4881-9c31-1c048e204602\",\"name\":\"Weekly Newsletter\"," +
            "\"description\":\"Weekly digest of content.\",\"subscription\":\"opt_out\"}" +
            "],\"has_more\":false}";

    private static final String UPDATE_TOPICS_JSON = "{\"id\":\"" + CONTACT_ID + "\"}";

    @Mock
    private IHttpClient httpClient;

    private ContactTopics contactTopics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactTopics = new ContactTopics("test-api-key", httpClient);
    }

    @Test
    public void testListTopicsById_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_TOPICS_JSON, true);

        when(httpClient.perform(eq("/contacts/" + CONTACT_ID + "/topics"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactTopicsResponse res = contactTopics.list(CONTACT_ID);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
        assertEquals("list", res.getObject());
        assertEquals("b6d24b8e-af0b-4c3c-be0c-359bbd97381e", res.getData().get(0).getId());
    }

    @Test
    public void testListTopicsByEmail_Success() throws ResendException {
        String contactEmail = "steve.wozniak@gmail.com";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_TOPICS_JSON, true);

        when(httpClient.perform(eq("/contacts/" + contactEmail + "/topics"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactTopicsResponse res = contactTopics.list(contactEmail);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListTopicsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_TOPICS_JSON, true);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactTopicsResponse res = contactTopics.list(CONTACT_ID, params);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testUpdateTopics_Success() throws ResendException {
        UpdateContactTopicsOptions options = ContactsUtil.createUpdateTopicsOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_TOPICS_JSON, true);

        when(httpClient.perform(eq("/contacts/" + CONTACT_ID + "/topics"), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateContactTopicsResponse res = contactTopics.update(options);

        assertNotNull(res);
        assertEquals(CONTACT_ID, res.getId());
    }

    @Test
    public void testListTopics_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Contact not found\"}", false);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> contactTopics.list(CONTACT_ID));
        assertEquals(404, (int) ex.getStatusCode());
    }
}
