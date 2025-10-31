package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.contacts.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend Contacts module.
 *
 * <p>This service handles global contact operations (CRUD for contacts not associated with specific segments).
 * For specialized operations, use the following sub-services:</p>
 *
 * <ul>
 *   <li>{@link #segments()} - Manage contact membership in segments (add/remove contacts from segments)</li>
 *   <li>{@link #topics()} - Manage contact topic subscriptions (list and update topic preferences)</li>
 * </ul>
 *
 * <p><strong>Global Contact Operations:</strong></p>
 * <ul>
 *   <li>{@link #create(CreateContactOptions)} - Create a global contact</li>
 *   <li>{@link #list()} - List all global contacts</li>
 *   <li>{@link #list(ListParams)} - List global contacts with pagination</li>
 *   <li>{@link #get(String)} - Get a global contact by ID or email</li>
 *   <li>{@link #update(UpdateContactOptions)} - Update a global contact</li>
 *   <li>{@link #remove(String)} - Remove a global contact</li>
 * </ul>
 *
 * <p><strong>Migration Note:</strong> Methods that accept {@code segmentId} or {@code audienceId} parameters
 * are deprecated. Use the Segments service for segment-specific operations instead.</p>
 */
public class Contacts extends BaseService {

    private ContactSegments contactSegments;
    private ContactTopics contactTopics;

    /**
     * Constructs an instance of the {@code Contacts} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Contacts(final String apiKey) {
        super(apiKey);
    }

    /**
     * Returns the ContactSegments sub-service for managing contact segment memberships.
     *
     * @return The ContactSegments service instance.
     */
    public ContactSegments segments() {
        if (this.contactSegments == null) {
            this.contactSegments = new ContactSegments(this.apiKey);
        }
        return this.contactSegments;
    }

    /**
     * Returns the ContactTopics sub-service for managing contact topic subscriptions.
     *
     * @return The ContactTopics service instance.
     */
    public ContactTopics topics() {
        if (this.contactTopics == null) {
            this.contactTopics = new ContactTopics(this.apiKey);
        }
        return this.contactTopics;
    }

    /**
     * Creates a global contact (not associated with any segment).
     *
     * <p>To add a contact to a segment, first create the global contact using this method,
     * then use {@code contacts().segments().add(options)} to add it to specific segments.</p>
     *
     * <p><strong>Note:</strong> The {@code segmentId} and {@code audienceId} fields in {@code CreateContactOptions}
     * are ignored. Use the workflow above for segment membership.</p>
     *
     * @param createContactOptions The Contact details.
     * @return The details of the created contact.
     * @throws ResendException If an error occurs during the Contact creation process.
     */
    public CreateContactResponseSuccess create(CreateContactOptions createContactOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createContactOptions);

        AbstractHttpResponse<String> response = httpClient.perform("/contacts", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateContactResponseSuccess.class);
    }

    /**
     * Retrieves a list of contacts in a segment.
     *
     * @deprecated This method is deprecated. Segment-related operations have been moved to dedicated services.
     *             For segment-specific contact operations, use the Segments service instead:
     *             {@code resend.segments().get(segmentId)} or contact operations through the Segments API.
     *             This method will be removed in a future version.
     *
     * @param segmentId The id of the segment (formerly known as audienceId).
     * @return A ListContactsResponseSuccess containing the list of contacts.
     * @throws ResendException If an error occurs during the contacts list retrieval process.
     */
    @Deprecated
    public ListContactsResponseSuccess list(String segmentId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/segments/" + segmentId + "/contacts" , super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of contacts in a segment.
     *
     * @deprecated This method is deprecated. Segment-related operations have been moved to dedicated services.
     *             For segment-specific contact operations, use the Segments service instead:
     *             {@code resend.segments().get(segmentId)} or contact operations through the Segments API.
     *             This method will be removed in a future version.
     *
     * @param segmentId The id of the segment (formerly known as audienceId).
     * @param params The params used to customize the list.
     * @return A ListContactsResponseSuccess containing the paginated list of contacts.
     * @throws ResendException If an error occurs during the contacts list retrieval process.
     */
    @Deprecated
    public ListContactsResponseSuccess list(String segmentId, ListParams params) throws ResendException {
        String pathWithQuery = "/segments/" + segmentId + "/contacts" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a list of global contacts (not associated with any segment).
     *
     * @return A ListContactsResponseSuccess containing the list of global contacts.
     * @throws ResendException If an error occurs during the contacts list retrieval process.
     */
    public ListContactsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/contacts", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of global contacts (not associated with any segment).
     *
     * @param params The params used to customize the list.
     * @return A ListContactsResponseSuccess containing the paginated list of global contacts.
     * @throws ResendException If an error occurs during the contacts list retrieval process.
     */
    public ListContactsResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/contacts" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a global contact by its unique identifier.
     *
     * <p><strong>Note:</strong> The {@code segmentId} and {@code audienceId} fields in {@code GetContactOptions}
     * are ignored. This method only retrieves global contacts. Use {@link #get(String)} for a simpler API.</p>
     *
     * @deprecated Use {@link #get(String)} instead for global contacts.
     *
     * @param params The object containing either {@code id} or {@code email} of the contact.
     * @return The retrieved contact details.
     * @throws ResendException If an error occurs while retrieving the contact.
     */
    @Deprecated
    public GetContactResponseSuccess get(GetContactOptions params) throws ResendException {
        String id    = params.getId();
        String email = params.getEmail();

        if ((id == null || id.isEmpty()) && (email == null || email.isEmpty())) {
            throw new IllegalArgumentException("Either contact id or email must be provided");
        }

        String contactIdentifier =  (id != null && !id.isEmpty()) ? id : email;

        AbstractHttpResponse<String> response = this.httpClient.perform("/contacts/" + contactIdentifier, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetContactResponseSuccess.class);
    }

    /**
     * Retrieves a global contact by its unique identifier (not associated with any segment).
     *
     * @param contactIdOrEmail The contact's id or email address.
     * @return The retrieved contact details.
     * @throws ResendException If an error occurs while retrieving the contact.
     */
    public GetContactResponseSuccess get(String contactIdOrEmail) throws ResendException {
        if (contactIdOrEmail == null || contactIdOrEmail.isEmpty()) {
            throw new IllegalArgumentException("Contact id or email must be provided");
        }

        AbstractHttpResponse<String> response = this.httpClient.perform("/contacts/" + contactIdOrEmail, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetContactResponseSuccess.class);
    }

    /**
     * Deletes a global contact based on the provided contact ID or email.
     *
     * <p><strong>Note:</strong> The {@code segmentId} and {@code audienceId} fields in {@code RemoveContactOptions}
     * are ignored. This method only removes global contacts. Use {@link #remove(String)} for a simpler API.</p>
     *
     * @deprecated Use {@link #remove(String)} instead for global contacts.
     *
     * @param params The object with identifier of the contact to delete.
     * @return The RemoveContactsResponseSuccess with the details of the removed contact.
     * @throws ResendException If an error occurs during the contact deletion process.
     */
    @Deprecated
    public RemoveContactResponseSuccess remove(RemoveContactOptions params) throws ResendException {
        if ((params.getId() == null && params.getEmail() == null) ||
                (params.getId() != null && params.getEmail() != null)) {
            throw new IllegalArgumentException("Either 'id' or 'email' must be provided, but not both.");
        }

        String pathParameter = params.getId() != null ? params.getId() : params.getEmail();

        AbstractHttpResponse<String> response = httpClient.perform("/contacts/" + pathParameter, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveContactResponseSuccess.class);
    }

    /**
     * Deletes a global contact based on the provided contact ID.
     * Note: Global contacts can only be removed by ID, not by email.
     *
     * @param contactId The contact's id.
     * @return The RemoveContactsResponseSuccess with the details of the removed contact.
     * @throws ResendException If an error occurs during the contact deletion process.
     */
    public RemoveContactResponseSuccess remove(String contactId) throws ResendException {
        if (contactId == null || contactId.isEmpty()) {
            throw new IllegalArgumentException("Contact id must be provided");
        }

        AbstractHttpResponse<String> response = httpClient.perform("/contacts/" + contactId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveContactResponseSuccess.class);
    }

    /**
     * Updates a global contact based on the provided contact ID or email.
     *
     * <p><strong>Note:</strong> The {@code segmentId} and {@code audienceId} fields in {@code UpdateContactOptions}
     * are ignored. This method only updates global contacts.</p>
     *
     * @param params The object with identifier of the contact to patch.
     * @return The UpdateContactResponseSuccess with the details of the patched contact.
     * @throws ResendException If an error occurs during the contact patching process.
     */
    public UpdateContactResponseSuccess update(UpdateContactOptions params) throws ResendException {
        if ((params.getId() == null && params.getEmail() == null) ||
                (params.getId() != null && params.getEmail() != null)) {
            throw new IllegalArgumentException("Either 'id' or 'email' must be provided, but not both.");
        }

        String pathParameter = params.getId() != null ? params.getId() : params.getEmail();

        String payload = super.resendMapper.writeValue(params);
        AbstractHttpResponse<String> response = httpClient.perform("/contacts/" + pathParameter, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, UpdateContactResponseSuccess.class);
    }

    /**
     * Retrieves a list of topic subscriptions for a contact.
     *
     * @deprecated This method is deprecated. Topics related operations have been moved to dedicated services.
     *             For contact-topics  operations, use the Contacts service instead:
     *             {@code resend.contacts().topics.list(contactIdOrEmail)}.
     *             This method will be removed in a future version.
     *
     * @param contactIdOrEmail The contact ID or email address.
     * @return A ListContactTopicsResponse containing the list of topic subscriptions.
     * @throws ResendException If an error occurs during the topic list retrieval process.
     */
    public ListContactTopicsResponse getTopics(String contactIdOrEmail) throws ResendException {
        if (contactIdOrEmail == null || contactIdOrEmail.isEmpty()) {
            throw new IllegalArgumentException("Contact ID or email must be provided");
        }

        AbstractHttpResponse<String> response = this.httpClient.perform("/contacts/" + contactIdOrEmail + "/topics", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactTopicsResponse.class);
    }

    /**
     * Retrieves a paginated list of topic subscriptions for a contact.
     *
     * @deprecated This method is deprecated. Topics related operations have been moved to dedicated services.
     *             For contact-topics  operations, use the Contacts service instead:
     *             {@code resend.contacts().topics.list(contactIdOrEmail, params)}.
     *             This method will be removed in a future version.
     *
     * @param contactIdOrEmail The contact ID or email address.
     * @param params           The params used to customize the list.
     * @return A ListContactTopicsResponse containing the paginated list of topic subscriptions.
     * @throws ResendException If an error occurs during the topic list retrieval process.
     */
    public ListContactTopicsResponse getTopics(String contactIdOrEmail, ListParams params) throws ResendException {
        if (contactIdOrEmail == null || contactIdOrEmail.isEmpty()) {
            throw new IllegalArgumentException("Contact ID or email must be provided");
        }

        String pathWithQuery = "/contacts/" + contactIdOrEmail + "/topics" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactTopicsResponse.class);
    }

    /**
     * Updates topic subscriptions for a contact.
     *
     * @deprecated This method is deprecated. Topics related operations have been moved to dedicated services.
     *             For contact-topics  operations, use the Contacts service instead:
     *             {@code resend.contacts().topics.list(options)}.
     *             This method will be removed in a future version.
     *
     * @param options The options containing the contact identifier and topic updates.
     * @return The UpdateContactTopicsResponse with the contact ID.
     * @throws ResendException If an error occurs during the topic update process.
     */
    public UpdateContactTopicsResponse updateTopics(UpdateContactTopicsOptions options) throws ResendException {
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
        AbstractHttpResponse<String> response = httpClient.perform("/contacts/" + contactIdOrEmail + "/topics", super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, UpdateContactTopicsResponse.class);
    }
}
