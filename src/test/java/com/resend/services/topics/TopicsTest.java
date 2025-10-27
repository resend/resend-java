package com.resend.services.topics;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.topics.model.*;
import com.resend.services.util.TopicsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for Topics service.
 */
public class TopicsTest {

    @Mock
    private Topics topics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topics = mock(Topics.class);
    }

    @Test
    public void testCreateTopic_Success() throws ResendException {
        CreateTopicOptions createOptions = TopicsUtil.createTopicOptions();
        CreateTopicResponse expectedResponse = TopicsUtil.createTopicResponse();

        when(topics.create(createOptions)).thenReturn(expectedResponse);

        CreateTopicResponse response = topics.create(createOptions);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        verify(topics, times(1)).create(createOptions);
    }

    @Test
    public void testGetTopic_Success() throws ResendException {
        Topic expectedTopic = TopicsUtil.createTopic();

        when(topics.get(expectedTopic.getId())).thenReturn(expectedTopic);

        Topic retrievedTopic = topics.get(expectedTopic.getId());

        assertNotNull(retrievedTopic);
        assertEquals(expectedTopic.getId(), retrievedTopic.getId());
        assertEquals(expectedTopic.getName(), retrievedTopic.getName());
        assertEquals(expectedTopic.getDescription(), retrievedTopic.getDescription());
        assertEquals(expectedTopic.getDefaultSubscription(), retrievedTopic.getDefaultSubscription());
        verify(topics, times(1)).get(expectedTopic.getId());
    }

    @Test
    public void testUpdateTopic_Success() throws ResendException {
        String topicId = "b6d24b8e-af0b-4c3c-be0c-359bbd97381e";
        UpdateTopicOptions updateOptions = TopicsUtil.updateTopicOptions();
        UpdateTopicResponse expectedResponse = TopicsUtil.updateTopicResponse();

        when(topics.update(topicId, updateOptions)).thenReturn(expectedResponse);

        UpdateTopicResponse response = topics.update(topicId, updateOptions);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        verify(topics, times(1)).update(topicId, updateOptions);
    }

    @Test
    public void testRemoveTopic_Success() throws ResendException {
        String topicId = "b6d24b8e-af0b-4c3c-be0c-359bbd97381e";
        RemoveTopicResponse expectedResponse = TopicsUtil.removeTopicResponse();

        when(topics.remove(topicId)).thenReturn(expectedResponse);

        RemoveTopicResponse response = topics.remove(topicId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(true, response.isDeleted());
        verify(topics, times(1)).remove(topicId);
    }

    @Test
    public void testListTopics_Success() throws ResendException {
        ListTopicsResponse expectedResponse = TopicsUtil.createListTopicsResponse();

        when(topics.list()).thenReturn(expectedResponse);

        ListTopicsResponse response = topics.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        assertEquals(expectedResponse.hasMore(), response.hasMore());
        verify(topics, times(1)).list();
    }

    @Test
    public void testListTopicsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(3)
                .build();
        ListTopicsResponse expectedResponse = TopicsUtil.createListTopicsResponse();

        when(topics.list(params)).thenReturn(expectedResponse);

        ListTopicsResponse response = topics.list(params);

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        verify(topics, times(1)).list(params);
    }
}
