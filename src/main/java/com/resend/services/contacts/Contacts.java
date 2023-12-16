package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
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
     * @param createContactRequestOptions The Contact details.
     * @return The details of the created contact.
     * @throws ResendException If an error occurs during the Contact creation process.
     */
    public CreateContactResponseSuccess create(CreateContactRequestOptions createContactRequestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createContactRequestOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" +createContactRequestOptions.getAudienceId()+ "/contacts" , super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to create contact: " + response.getCode() + " " + response.getBody());
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
            throw new ResendException("Failed to retrieve contacts: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactsResponseSuccess.class);
    }

    /**
     * Retrieves a contact by its unique identifier.
     *
     * @param params The object with identifier of the contact to delete and its audience id.
     * @return The retrieved contact details.
     * @throws ResendException If an error occurs while retrieving the contact.
     */
    public GetContactResponseSuccess get(ContactRequestOptions params) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences/" +params.getAudienceId()+ "/contacts/" +params.getId(), super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to retrieve contact: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetContactResponseSuccess.class);
    }

    /**
     * Deletes a contact based on the provided contact ID.
     *
     * @param params The object with identifier of the contact to delete and its audience id.
     * @return The RemoveContactsResponseSuccess with the details of the removed contact.
     * @throws ResendException If an error occurs during the contact deletion process.
     */
    public RemoveContactResponseSuccess remove(ContactRequestOptions params) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" +params.getAudienceId()+ "/contacts/" +params.getId(), super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to delete contact: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveContactResponseSuccess.class);
    }
}
