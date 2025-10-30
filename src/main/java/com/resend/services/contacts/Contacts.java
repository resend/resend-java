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
 *  Represents the Resend Contacts module.
 */
public class Contacts extends BaseService {

    /**
     * Constructs an instance of the {@code Contacts} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Contacts(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a Contact.
     *
     * @param createContactOptions The Contact details.
     * @return The details of the created contact.
     * @throws ResendException If an error occurs during the Contact creation process.
     */
    public CreateContactResponseSuccess create(CreateContactOptions createContactOptions) throws ResendException {
        String segmentId = createContactOptions.getSegmentId() != null ? createContactOptions.getSegmentId() : createContactOptions.getAudienceId();
        String payload = super.resendMapper.writeValue(createContactOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" + segmentId + "/contacts" , super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateContactResponseSuccess.class);
    }

    /**
     * Retrieves a list of contacts and returns a ListContactsResponseSuccess.
     *
     * @param audienceId The id of the audience.
     * @return A ListContactsResponseSuccess containing the list of contacts.
     * @throws ResendException If an error occurs during the contacts list retrieval process.
     */
    public ListContactsResponseSuccess list(String audienceId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences/" +audienceId+ "/contacts" , super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of contacts and returns a ListContactsResponseSuccess.
     *
     * @param audienceId The id of the audience.
     * @param params The params used to customize the list.
     * @return A ListContactsResponseSuccess containing the paginated list of contacts.
     * @throws ResendException If an error occurs during the contacts list retrieval process.
     */
    public ListContactsResponseSuccess list(String audienceId, ListParams params) throws ResendException {
        String pathWithQuery = "/audiences/" +audienceId+ "/contacts" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a contact by its unique identifier.
     *
     * @param params The object containing:
     *               – {@code audienceId}: the audience to which the contact belongs
     *               – Either {@code id}: the contact’s id
     *                 or {@code email}: the contact’s email address
     * @return The retrieved contact details.
     * @throws ResendException If an error occurs while retrieving the contact.
     */
    public GetContactResponseSuccess get(GetContactOptions params) throws ResendException {
        String id    = params.getId();
        String email = params.getEmail();

        if ((id == null || id.isEmpty()) && (email == null || email.isEmpty())) {
            throw new IllegalArgumentException("Either contact id or email must be provided");
        }

        String contactIdentifier =  (id != null && !id.isEmpty()) ? id : email;
        String segmentId = params.getSegmentId() != null ? params.getSegmentId() : params.getAudienceId();

        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences/" + segmentId + "/contacts/" + contactIdentifier, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetContactResponseSuccess.class);
    }

    /**
     * Deletes a contact based on the provided contact ID or email.
     *
     * @param params The object with identifier of the contact to delete.
     * @return The RemoveContactsResponseSuccess with the details of the removed contact.
     * @throws ResendException If an error occurs during the contact deletion process.
     */
    public RemoveContactResponseSuccess remove(RemoveContactOptions params) throws ResendException {
        if ((params.getId() == null && params.getEmail() == null) ||
                (params.getId() != null && params.getEmail() != null)) {
            throw new IllegalArgumentException("Either 'id' or 'email' must be provided, but not both.");
        }

        String pathParameter = params.getId() != null ? params.getId() : params.getEmail();
        String segmentId = params.getSegmentId() != null ? params.getSegmentId() : params.getAudienceId();

        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" + segmentId + "/contacts/" + pathParameter, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveContactResponseSuccess.class);
    }

    /**
     * Updates a contact based on the provided contact ID or email.
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
        String segmentId = params.getSegmentId() != null ? params.getSegmentId() : params.getAudienceId();

        String payload = super.resendMapper.writeValue(params);
        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" + segmentId + "/contacts/" + pathParameter, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, UpdateContactResponseSuccess.class);
    }
}
