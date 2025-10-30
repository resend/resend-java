package com.resend.services.contactproperties;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.service.BaseService;
import com.resend.services.contactproperties.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend ContactProperties module.
 */
public class ContactProperties extends BaseService {

    /**
     * Constructs an instance of the {@code ContactProperties} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public ContactProperties(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a ContactProperty.
     *
     * @param createContactPropertyOptions The ContactProperty details.
     * @return The details of the created contact property.
     * @throws ResendException If an error occurs during the ContactProperty creation process.
     */
    public CreateContactPropertyResponseSuccess create(CreateContactPropertyOptions createContactPropertyOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createContactPropertyOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/contact-properties", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateContactPropertyResponseSuccess.class);
    }

    /**
     * Retrieves a list of contact properties and returns a ListContactPropertiesResponseSuccess.
     *
     * @return A ListContactPropertiesResponseSuccess containing the list of contact properties.
     * @throws ResendException If an error occurs during the contact properties list retrieval process.
     */
    public ListContactPropertiesResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/contact-properties", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListContactPropertiesResponseSuccess.class);
    }

    /**
     * Retrieves a contact property by its unique identifier.
     *
     * @param id The contact property's id.
     * @return The retrieved contact property details.
     * @throws ResendException If an error occurs while retrieving the contact property.
     */
    public ContactProperty get(String id) throws ResendException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Contact property id must be provided");
        }

        AbstractHttpResponse<String> response = this.httpClient.perform("/contact-properties/" + id, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ContactProperty.class);
    }

    /**
     * Updates a contact property based on the provided contact property ID.
     *
     * @param updateContactPropertyOptions The object with the contact property id and fallback value to update.
     * @return The UpdateContactPropertyResponseSuccess with the details of the updated contact property.
     * @throws ResendException If an error occurs during the contact property update process.
     */
    public UpdateContactPropertyResponseSuccess update(UpdateContactPropertyOptions updateContactPropertyOptions) throws ResendException {
        if (updateContactPropertyOptions.getId() == null || updateContactPropertyOptions.getId().isEmpty()) {
            throw new IllegalArgumentException("Contact property id must be provided");
        }

        String payload = super.resendMapper.writeValue(updateContactPropertyOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/contact-properties/" + updateContactPropertyOptions.getId(), super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, UpdateContactPropertyResponseSuccess.class);
    }

    /**
     * Deletes a contact property based on the provided contact property ID.
     *
     * @param id The identifier of the contact property to delete.
     * @return The RemoveContactPropertyResponseSuccess with the details of the removed contact property.
     * @throws ResendException If an error occurs during the contact property deletion process.
     */
    public RemoveContactPropertyResponseSuccess remove(String id) throws ResendException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Contact property id must be provided");
        }

        AbstractHttpResponse<String> response = httpClient.perform("/contact-properties/" + id, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveContactPropertyResponseSuccess.class);
    }
}
