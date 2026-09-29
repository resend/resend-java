package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.contacts.model.ListContactTopicsResponse;
import com.resend.services.contacts.model.UpdateContactTopicsOptions;
import com.resend.services.contacts.model.UpdateContactTopicsResponse;
import okhttp3.MediaType;

/**
 * Represents the Contact Topics sub-service.
 * Handles operations for managing contact topic subscriptions.
 */
public class ContactTopics extends BaseService {

    /**
     * Constructs an instance of the {@code ContactTopics} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public ContactTopics(final String apiKey) {
        super(apiKey);
    }

    ContactTopics(final String apiKey, final IHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves a list of topic subscriptions for a contact.
     *
     * @param contactIdOrEmail The contact ID or email address.
     * @return A ListContactTopicsResponse containing the list of topic subscriptions.
     * @throws ResendException If an error occurs during the topic list retrieval process.
     */
    public ListContactTopicsResponse list(String contactIdOrEmail) throws ResendException {
        if (contactIdOrEmail == null || contactIdOrEmail.isEmpty()) {
            throw new IllegalArgumentException("Contact ID or email must be provided");
        }

        return execute("/contacts/" + contactIdOrEmail + "/topics", HttpMethod.GET, null, MediaType.get("application/json"), ListContactTopicsResponse.class);
    }

    /**
     * Retrieves a paginated list of topic subscriptions for a contact.
     *
     * @param contactIdOrEmail The contact ID or email address.
     * @param params           The params used to customize the list.
     * @return A ListContactTopicsResponse containing the paginated list of topic subscriptions.
     * @throws ResendException If an error occurs during the topic list retrieval process.
     */
    public ListContactTopicsResponse list(String contactIdOrEmail, ListParams params) throws ResendException {
        if (contactIdOrEmail == null || contactIdOrEmail.isEmpty()) {
            throw new IllegalArgumentException("Contact ID or email must be provided");
        }

        String pathWithQuery = "/contacts/" + contactIdOrEmail + "/topics" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListContactTopicsResponse.class);
    }

    /**
     * Updates topic subscriptions for a contact.
     *
     * @param options The options containing the contact identifier and topic updates.
     * @return The UpdateContactTopicsResponse with the contact ID.
     * @throws ResendException If an error occurs during the topic update process.
     */
    public UpdateContactTopicsResponse update(UpdateContactTopicsOptions options) throws ResendException {
        if ((options.getId() == null && options.getEmail() == null) ||
                (options.getId() != null && options.getEmail() != null)) {
            throw new IllegalArgumentException("Either 'id' or 'email' must be provided, but not both.");
        }

        if (options.getTopics() == null || options.getTopics().isEmpty()) {
            throw new IllegalArgumentException("Topics list must not be empty");
        }

        String contactIdOrEmail = options.getId() != null ? options.getId() : options.getEmail();

        // Serialize just the topics array (not the whole options object)
        String payload = super.resendMapper.writeValue(options.getTopics());
        return execute("/contacts/" + contactIdOrEmail + "/topics", HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateContactTopicsResponse.class);
    }
}
