package com.resend.services.contactproperties;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
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
     * Constructs an instance of the {@code ContactProperties} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public ContactProperties(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a ContactProperty.
     *
     * @param createContactPropertyOptions The ContactProperty details.
     * @return The details of the created contact property.
     * @throws ResendException If an error occurs during the ContactProperty creation process.
     */
    public CreateContactPropertyResponseSuccess create(CreateContactPropertyOptions createContactPropertyOptions) throws ResendException {
        return create(createContactPropertyOptions, (RequestOptions) null);
    }

    /**
     * Creates a ContactProperty.
     *
     * @param createContactPropertyOptions The ContactProperty details.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The details of the created contact property.
     * @throws ResendException If an error occurs during the ContactProperty creation process.
     */
    public CreateContactPropertyResponseSuccess create(CreateContactPropertyOptions createContactPropertyOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createContactPropertyOptions);
        return execute("/contact-properties", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateContactPropertyResponseSuccess.class);
    }

    /**
     * Retrieves a list of contact properties and returns a ListContactPropertiesResponseSuccess.
     *
     * @return A ListContactPropertiesResponseSuccess containing the list of contact properties.
     * @throws ResendException If an error occurs during the contact properties list retrieval process.
     */
    public ListContactPropertiesResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of contact properties and returns a ListContactPropertiesResponseSuccess.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListContactPropertiesResponseSuccess containing the list of contact properties.
     * @throws ResendException If an error occurs during the contact properties list retrieval process.
     */
    public ListContactPropertiesResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/contact-properties", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListContactPropertiesResponseSuccess.class);
    }

    /**
     * Retrieves a contact property by its unique identifier.
     *
     * @param id The contact property's id.
     * @return The retrieved contact property details.
     * @throws ResendException If an error occurs while retrieving the contact property.
     */
    public ContactProperty get(String id) throws ResendException {
        return get(id, (RequestOptions) null);
    }

    /**
     * Retrieves a contact property by its unique identifier.
     *
     * @param id The contact property's id.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved contact property details.
     * @throws ResendException If an error occurs while retrieving the contact property.
     */
    public ContactProperty get(String id, RequestOptions requestOptions) throws ResendException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Contact property id must be provided");
        }

        return execute("/contact-properties/" + id, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ContactProperty.class);
    }

    /**
     * Updates a contact property based on the provided contact property ID.
     *
     * @param updateContactPropertyOptions The object with the contact property id and fallback value to update.
     * @return The UpdateContactPropertyResponseSuccess with the details of the updated contact property.
     * @throws ResendException If an error occurs during the contact property update process.
     */
    public UpdateContactPropertyResponseSuccess update(UpdateContactPropertyOptions updateContactPropertyOptions) throws ResendException {
        return update(updateContactPropertyOptions, (RequestOptions) null);
    }

    /**
     * Updates a contact property based on the provided contact property ID.
     *
     * @param updateContactPropertyOptions The object with the contact property id and fallback value to update.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The UpdateContactPropertyResponseSuccess with the details of the updated contact property.
     * @throws ResendException If an error occurs during the contact property update process.
     */
    public UpdateContactPropertyResponseSuccess update(UpdateContactPropertyOptions updateContactPropertyOptions, RequestOptions requestOptions) throws ResendException {
        if (updateContactPropertyOptions.getId() == null || updateContactPropertyOptions.getId().isEmpty()) {
            throw new IllegalArgumentException("Contact property id must be provided");
        }

        String payload = super.resendMapper.writeValue(updateContactPropertyOptions);
        return execute("/contact-properties/" + updateContactPropertyOptions.getId(), HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateContactPropertyResponseSuccess.class);
    }

    /**
     * Deletes a contact property based on the provided contact property ID.
     *
     * @param id The identifier of the contact property to delete.
     * @return The RemoveContactPropertyResponseSuccess with the details of the removed contact property.
     * @throws ResendException If an error occurs during the contact property deletion process.
     */
    public RemoveContactPropertyResponseSuccess remove(String id) throws ResendException {
        return remove(id, (RequestOptions) null);
    }

    /**
     * Deletes a contact property based on the provided contact property ID.
     *
     * @param id The identifier of the contact property to delete.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The RemoveContactPropertyResponseSuccess with the details of the removed contact property.
     * @throws ResendException If an error occurs during the contact property deletion process.
     */
    public RemoveContactPropertyResponseSuccess remove(String id, RequestOptions requestOptions) throws ResendException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Contact property id must be provided");
        }

        return execute("/contact-properties/" + id, HttpMethod.DELETE, "", null, requestOptions, RemoveContactPropertyResponseSuccess.class);
    }
}
