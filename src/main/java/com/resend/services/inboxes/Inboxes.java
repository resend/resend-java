package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.inboxes.model.CreateInboxOptions;
import com.resend.services.inboxes.model.CreateInboxResponseSuccess;
import com.resend.services.inboxes.model.GetInboxResponseSuccess;
import com.resend.services.inboxes.model.ListInboxesResponseSuccess;
import com.resend.services.inboxes.model.RemoveInboxResponseSuccess;
import com.resend.services.inboxes.model.UpdateInboxOptions;
import com.resend.services.inboxes.model.UpdateInboxResponseSuccess;
import okhttp3.MediaType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Represents the Resend Inboxes module.
 */
public class Inboxes extends BaseService {

    /**
     * Constructs an instance of the {@code Inboxes} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Inboxes(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Inboxes} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Inboxes(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Returns an InboxAgent object that can be used to read and update the agent settings of an inbox.
     *
     * @return An InboxAgent object.
     */
    public InboxAgent agent() {
        return new InboxAgent(super.apiKey, super.httpClient);
    }

    /**
     * Returns an InboxLabels object that can be used to manage the labels of an inbox.
     *
     * @return An InboxLabels object.
     */
    public InboxLabels labels() {
        return new InboxLabels(super.apiKey, super.httpClient);
    }

    /**
     * Returns an InboxThreads object that can be used to manage the threads of an inbox.
     *
     * @return An InboxThreads object.
     */
    public InboxThreads threads() {
        return new InboxThreads(super.apiKey, super.httpClient);
    }

    /**
     * Returns an InboxDrafts object that can be used to manage the drafts of an inbox.
     *
     * @return An InboxDrafts object.
     */
    public InboxDrafts drafts() {
        return new InboxDrafts(super.apiKey, super.httpClient);
    }

    /**
     * Creates an inbox that sends and receives email at an address on one of your domains.
     * A domain that does not meet the inbox rules returns a 422. An address that is already in use returns a 409.
     * A team that has reached the inbox limit of its plan returns a 403. A blocked account returns a 451.
     *
     * @param createInboxOptions The inbox details.
     * @return The ID of the created inbox.
     * @throws ResendException If an error occurs while creating the inbox.
     */
    public CreateInboxResponseSuccess create(CreateInboxOptions createInboxOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createInboxOptions);
        return execute("/inboxes", HttpMethod.POST, payload, MediaType.get("application/json"), CreateInboxResponseSuccess.class);
    }

    /**
     * Retrieves the inboxes of the team, newest first.
     * The list items carry fewer fields than a single inbox. Retrieve an inbox to get all its fields.
     *
     * @return A ListInboxesResponseSuccess containing the list of inboxes.
     * @throws ResendException If an error occurs while retrieving the inboxes.
     */
    public ListInboxesResponseSuccess list() throws ResendException {
        return list(null);
    }

    /**
     * Retrieves a paginated list of the inboxes of the team, newest first.
     * The list items carry fewer fields than a single inbox. Retrieve an inbox to get all its fields.
     * The {@code after} and {@code before} cursors take an inbox ID. A value that is not a UUID returns a 422,
     * and an unknown ID returns a 404.
     *
     * @param params The params used to customize the list.
     * @return A ListInboxesResponseSuccess containing the list of inboxes.
     * @throws ResendException If an error occurs while retrieving the inboxes.
     */
    public ListInboxesResponseSuccess list(ListParams params) throws ResendException {
        return execute("/inboxes" + URLHelper.parse(params), HttpMethod.GET, null, MediaType.get("application/json"), ListInboxesResponseSuccess.class);
    }

    /**
     * Retrieves a single inbox by its ID or its email address.
     * A value that contains {@code @} must be a valid email address, and the match is case-insensitive.
     *
     * @param inboxIdOrEmail The inbox ID or the inbox email address.
     * @return The inbox.
     * @throws ResendException If an error occurs while retrieving the inbox.
     */
    public GetInboxResponseSuccess get(String inboxIdOrEmail) throws ResendException {
        return execute("/inboxes/" + encodePathSegment(inboxIdOrEmail), HttpMethod.GET, null, MediaType.get("application/json"), GetInboxResponseSuccess.class);
    }

    /**
     * Updates the internal name of an inbox or the name recipients see when mail is sent from it.
     * At least one of {@code name} or {@code from_name} is required.
     *
     * @param inboxId            The inbox ID. An email address is not accepted.
     * @param updateInboxOptions The fields to update.
     * @return The ID of the updated inbox.
     * @throws ResendException If an error occurs while updating the inbox.
     */
    public UpdateInboxResponseSuccess update(String inboxId, UpdateInboxOptions updateInboxOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateInboxOptions);
        return execute("/inboxes/" + inboxId, HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateInboxResponseSuccess.class);
    }

    /**
     * Removes an inbox. An inbox that does not exist or was already removed returns a 404.
     *
     * @param inboxId The inbox ID. An email address is not accepted.
     * @return The RemoveInboxResponseSuccess with the details of the removed inbox.
     * @throws ResendException If an error occurs while removing the inbox.
     */
    public RemoveInboxResponseSuccess remove(String inboxId) throws ResendException {
        return execute("/inboxes/" + inboxId, HttpMethod.DELETE, null, MediaType.get("application/json"), RemoveInboxResponseSuccess.class);
    }

    private static String encodePathSegment(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name()).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
