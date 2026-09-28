package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.contacts.model.*;
import okhttp3.MediaType;

/**
 * Represents the Contact Segments sub-service.
 * Handles operations for managing contact membership in segments.
 */
public class ContactSegments extends BaseService {

    /**
     * Constructs an instance of the {@code ContactSegments} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public ContactSegments(final String apiKey) {
        super(apiKey);
    }

    ContactSegments(final String apiKey, final IHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Adds an existing contact to a segment.
     *
     * @param options The options containing the contact identifier (id or email) and segment ID.
     * @return The AddContactToSegmentResponseSuccess with the segment ID.
     * @throws ResendException If an error occurs during the segment addition process.
     */
    public AddContactToSegmentResponseSuccess add(AddContactToSegmentOptions options) throws ResendException {
        if ((options.getId() == null && options.getEmail() == null) ||
                (options.getId() != null && options.getEmail() != null)) {
            throw new IllegalArgumentException("Either 'id' or 'email' must be provided, but not both.");
        }

        if (options.getSegmentId() == null || options.getSegmentId().isEmpty()) {
            throw new IllegalArgumentException("Segment ID must be provided");
        }

        String contactIdOrEmail = options.getId() != null ? options.getId() : options.getEmail();
        String endpoint = "/contacts/" + contactIdOrEmail + "/segments/" + options.getSegmentId();

        return execute(endpoint, HttpMethod.POST, "", MediaType.get("application/json"), AddContactToSegmentResponseSuccess.class);
    }

    /**
     * Removes an existing contact from a segment.
     *
     * @param options The options containing the contact identifier (id or email) and segment ID.
     * @return The RemoveContactFromSegmentResponseSuccess with the segment ID and deletion status.
     * @throws ResendException If an error occurs during the segment removal process.
     */
    public RemoveContactFromSegmentResponseSuccess remove(RemoveContactFromSegmentOptions options) throws ResendException {
        if ((options.getId() == null && options.getEmail() == null) ||
                (options.getId() != null && options.getEmail() != null)) {
            throw new IllegalArgumentException("Either 'id' or 'email' must be provided, but not both.");
        }

        if (options.getSegmentId() == null || options.getSegmentId().isEmpty()) {
            throw new IllegalArgumentException("Segment ID must be provided");
        }

        String contactIdOrEmail = options.getId() != null ? options.getId() : options.getEmail();
        String endpoint = "/contacts/" + contactIdOrEmail + "/segments/" + options.getSegmentId();

        return execute(endpoint, HttpMethod.DELETE, "", null, RemoveContactFromSegmentResponseSuccess.class);
    }

    /**
     * Retrieves a list of segments that a contact belongs to.
     *
     * @param contactIdOrEmail The contact ID or email address.
     * @return The ListContactSegmentsResponseSuccess with the list of segments.
     * @throws ResendException If an error occurs during the segment list retrieval process.
     */
    public ListContactSegmentsResponseSuccess list(String contactIdOrEmail) throws ResendException {
        if (contactIdOrEmail == null || contactIdOrEmail.isEmpty()) {
            throw new IllegalArgumentException("Contact ID or email must be provided");
        }

        String endpoint = "/contacts/" + contactIdOrEmail + "/segments";
        return execute(endpoint, HttpMethod.GET, null, MediaType.get("application/json"), ListContactSegmentsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of segments that a contact belongs to.
     *
     * @param contactId The contact ID.
     * @param params           The params used to customize the list.
     * @return The ListContactSegmentsResponseSuccess with the paginated list of segments.
     * @throws ResendException If an error occurs during the segment list retrieval process.
     */
    public ListContactSegmentsResponseSuccess list(String contactId, ListParams params) throws ResendException {
        if (contactId == null || contactId.isEmpty()) {
            throw new IllegalArgumentException("Contact ID must be provided");
        }

        String pathWithQuery = "/contacts/" + contactId + "/segments" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListContactSegmentsResponseSuccess.class);
    }
}
