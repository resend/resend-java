package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.inboxes.model.GetInboxAgentResponseSuccess;
import com.resend.services.inboxes.model.UpdateInboxAgentOptions;
import com.resend.services.inboxes.model.UpdateInboxAgentResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Inbox Agent module.
 */
public class InboxAgent extends BaseService {

    /**
     * Constructs an instance of the {@code InboxAgent} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public InboxAgent(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code InboxAgent} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public InboxAgent(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves the agent settings of an inbox. An inbox without a configured agent returns empty settings:
     * {@code instructions} and {@code tone} are null and {@code actions} is an empty list.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @return The agent settings of the inbox.
     * @throws ResendException If an error occurs while retrieving the agent settings.
     */
    public GetInboxAgentResponseSuccess get(String inboxId) throws ResendException {
        return execute("/inboxes/" + inboxId + "/agent", HttpMethod.GET, null, MediaType.get("application/json"), GetInboxAgentResponseSuccess.class);
    }

    /**
     * Updates the agent settings of an inbox. The agent is created when the inbox has none.
     * Omitted fields keep their current value.
     *
     * @param inboxId                 The inbox ID or the inbox email address.
     * @param updateInboxAgentOptions The settings to update.
     * @return The ID of the inbox agent.
     * @throws ResendException If an error occurs while updating the agent settings.
     */
    public UpdateInboxAgentResponseSuccess update(String inboxId, UpdateInboxAgentOptions updateInboxAgentOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateInboxAgentOptions);
        return execute("/inboxes/" + inboxId + "/agent", HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateInboxAgentResponseSuccess.class);
    }
}
