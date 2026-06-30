package com.resend.services.topics;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.topics.model.*;
import com.resend.services.util.TopicsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class TopicsTest {

    private static final String TOPIC_ID = "b6d24b8e-af0b-4c3c-be0c-359bbd97381e";
    private static final String CREATE_RESPONSE_JSON = "{\"id\":\"" + TOPIC_ID + "\"}";
    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"" + TOPIC_ID + "\",\"name\":\"Weekly Newsletter\"," +
            "\"description\":\"Weekly newsletter for our subscribers\"," +
            "\"default_subscription\":\"opt_in\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}";
    private static final String UPDATE_RESPONSE_JSON = "{\"id\":\"" + TOPIC_ID + "\"}";
    private static final String REMOVE_RESPONSE_JSON = "{\"object\":\"topic\",\"id\":\"" + TOPIC_ID + "\",\"deleted\":true}";
    private static final String LIST_RESPONSE_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"" + TOPIC_ID + "\",\"name\":\"Weekly Newsletter\"," +
            "\"description\":\"Weekly newsletter for our subscribers\"," +
            "\"default_subscription\":\"opt_in\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}," +
            "{\"id\":\"c7e35c9f-bg1c-5d4d-cf1d-460cce08492f\",\"name\":\"Monthly Updates\"," +
            "\"description\":\"Monthly updates and announcements\"," +
            "\"default_subscription\":\"opt_out\",\"created_at\":\"2023-04-09T00:11:13.110779+00:00\"}," +
            "{\"id\":\"d8f46da0-ch2d-6e5e-dg2e-571ddf19503g\",\"name\":\"Product Launches\"," +
            "\"description\":\"Get notified about new product launches\"," +
            "\"default_subscription\":\"opt_in\",\"created_at\":\"2023-04-10T00:11:13.110779+00:00\"}" +
            "]}";

    @Mock
    private IHttpClient httpClient;

    private Topics topics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topics = new Topics("test-api-key", httpClient);
    }

    @Test
    public void testCreateTopic_Success() throws ResendException {
        CreateTopicOptions createOptions = TopicsUtil.createTopicOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/topics"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateTopicResponseSuccess response = topics.create(createOptions);

        assertNotNull(response);
        assertEquals(TOPIC_ID, response.getId());
    }

    @Test
    public void testCreateTopic_ApiError_ThrowsResendException() throws ResendException {
        CreateTopicOptions createOptions = TopicsUtil.createTopicOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422, "{\"name\":\"validation_error\",\"message\":\"Invalid\"}", false);

        when(httpClient.perform(eq("/topics"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> topics.create(createOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetTopic_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/topics/" + TOPIC_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetTopicResponseSuccess response = topics.get(TOPIC_ID);

        assertNotNull(response);
        assertEquals(TOPIC_ID, response.getId());
        assertEquals("Weekly Newsletter", response.getName());
        assertEquals("opt_in", response.getDefaultSubscription());
    }

    @Test
    public void testUpdateTopic_Success() throws ResendException {
        UpdateTopicOptions updateOptions = TopicsUtil.updateTopicOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/topics/" + TOPIC_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateTopicResponseSuccess response = topics.update(TOPIC_ID, updateOptions);

        assertNotNull(response);
        assertEquals(TOPIC_ID, response.getId());
    }

    @Test
    public void testRemoveTopic_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/topics/" + TOPIC_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        RemoveTopicResponseSuccess response = topics.remove(TOPIC_ID);

        assertNotNull(response);
        assertEquals(TOPIC_ID, response.getId());
        assertTrue(response.isDeleted());
    }

    @Test
    public void testListTopics_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/topics"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListTopicsResponseSuccess response = topics.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals(false, response.hasMore());
    }

    @Test
    public void testListTopicsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/topics?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListTopicsResponseSuccess response = topics.list(params);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }
}
