package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.inboxes.model.*;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static com.resend.services.util.InboxesUtil.INBOX_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class InboxAgentTest {

    private static final String AGENT_PATH = "/inboxes/" + INBOX_ID + "/agent";
    private static final String UPDATE_RESPONSE_JSON =
            "{\"object\":\"inbox_agent\",\"id\":\"a1f84a4e-6f2b-4f0a-9c1d-8a2e5b3c7d90\"}";

    @Mock
    private IHttpClient httpClient;

    private InboxAgent agent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        agent = new Inboxes("test-api-key", httpClient).agent();
    }

    @Test
    public void testGetAgent_Success() throws ResendException {
        String json = "{\"object\":\"inbox_agent\",\"instructions\":null,\"tone\":\"friendly and concise\"," +
                "\"actions\":[\"draft_reply\",\"add_labels\"]}";
        when(httpClient.perform(eq(AGENT_PATH), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, json, true));

        GetInboxAgentResponseSuccess response = agent.get(INBOX_ID);

        assertEquals("inbox_agent", response.getObject());
        assertNull(response.getInstructions());
        assertEquals("friendly and concise", response.getTone());
        assertEquals(Arrays.asList("draft_reply", "add_labels"), response.getActions());
    }

    @Test
    public void testUpdateAgent_SendsOnlySetFields() throws ResendException {
        UpdateInboxAgentOptions options = UpdateInboxAgentOptions.builder()
                .tone("friendly and concise")
                .actions(InboxAgentAction.DRAFT_REPLY, InboxAgentAction.ADD_LABELS)
                .build();
        when(httpClient.perform(eq(AGENT_PATH), anyString(), eq(HttpMethod.PATCH),
                eq("{\"tone\":\"friendly and concise\",\"actions\":[\"draft_reply\",\"add_labels\"]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true));

        UpdateInboxAgentResponseSuccess response = agent.update(INBOX_ID, options);

        assertEquals("inbox_agent", response.getObject());
        assertEquals("a1f84a4e-6f2b-4f0a-9c1d-8a2e5b3c7d90", response.getId());
    }

    @Test
    public void testUpdateAgent_SendsNullToClear() throws ResendException {
        UpdateInboxAgentOptions options = UpdateInboxAgentOptions.builder()
                .instructions(null)
                .actions(Collections.<InboxAgentAction>emptyList())
                .build();
        when(httpClient.perform(eq(AGENT_PATH), anyString(), eq(HttpMethod.PATCH),
                eq("{\"instructions\":null,\"actions\":[]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true));

        assertNotNull(agent.update(INBOX_ID, options));
        assertNull(options.getInstructions());
        assertTrue(options.getActions().isEmpty());
    }

    @Test
    public void testUpdateAgentBuilder_RequiresAField() {
        assertThrows(IllegalArgumentException.class, () -> UpdateInboxAgentOptions.builder().build());
    }

    @Test
    public void testUpdateAgent_SendsArchiveAndMoveToTrash() throws ResendException {
        UpdateInboxAgentOptions options = UpdateInboxAgentOptions.builder()
                .actions(InboxAgentAction.ARCHIVE, InboxAgentAction.MOVE_TO_TRASH)
                .build();
        when(httpClient.perform(eq(AGENT_PATH), anyString(), eq(HttpMethod.PATCH),
                eq("{\"actions\":[\"archive\",\"move_to_trash\"]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true));

        assertNotNull(agent.update(INBOX_ID, options));
    }

    @Test
    public void testAgentAction_UnknownValue_Throws() {
        assertEquals(InboxAgentAction.MARK_AS_SPAM, InboxAgentAction.fromValue("mark_as_spam"));
        assertThrows(IllegalArgumentException.class, () -> InboxAgentAction.fromValue("send_email"));
    }
}
