package com.resend.services.receiving;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.receiving.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend Receiving module for inbound emails.
 */
public final class Receiving extends BaseService {

    /**
     * Constructs an instance of the {@code Receiving} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Receiving(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Receiving} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Receiving(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves a single received email by its ID.
     *
     * @param emailId The unique identifier of the received email.
     * @return The retrieved received email.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public ReceivedEmail get(String emailId) throws ResendException {
        return get(emailId, (RequestOptions) null);
    }

    /**
     * Retrieves a single received email by its ID.
     *
     * @param emailId The unique identifier of the received email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved received email.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public ReceivedEmail get(String emailId, RequestOptions requestOptions) throws ResendException {
        return execute("/emails/receiving/" + emailId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ReceivedEmail.class);
    }

    /**
     * Retrieves a list of all received emails.
     *
     * @return A ListReceivedEmailsResponse containing the list of received emails.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListReceivedEmailsResponse list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of all received emails.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListReceivedEmailsResponse containing the list of received emails.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListReceivedEmailsResponse list(RequestOptions requestOptions) throws ResendException {
        return execute("/emails/receiving", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListReceivedEmailsResponse.class);
    }

    /**
     * Retrieves a paginated list of received emails.
     *
     * @param params The params used to customize the list.
     * @return A ListReceivedEmailsResponse containing the paginated list of received emails.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListReceivedEmailsResponse list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of received emails.
     *
     * @param params The params used to customize the list.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListReceivedEmailsResponse containing the paginated list of received emails.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListReceivedEmailsResponse list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/emails/receiving" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListReceivedEmailsResponse.class);
    }

    /**
     * Retrieves a single attachment from a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @param attachmentId The unique identifier of the attachment.
     * @return The attachment details including download URL.
     * @throws ResendException If an error occurs while retrieving the attachment.
     */
    public AttachmentDetails getAttachment(String emailId, String attachmentId) throws ResendException {
        return getAttachment(emailId, attachmentId, (RequestOptions) null);
    }

    /**
     * Retrieves a single attachment from a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @param attachmentId The unique identifier of the attachment.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The attachment details including download URL.
     * @throws ResendException If an error occurs while retrieving the attachment.
     */
    public AttachmentDetails getAttachment(String emailId, String attachmentId, RequestOptions requestOptions) throws ResendException {
        return execute("/emails/receiving/" + emailId + "/attachments/" + attachmentId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, AttachmentDetails.class);
    }

    /**
     * Retrieves a list of all attachments for a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @return A ListAttachmentsResponse containing the list of attachments.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListAttachmentsResponse listAttachments(String emailId) throws ResendException {
        return listAttachments(emailId, (RequestOptions) null);
    }

    /**
     * Retrieves a list of all attachments for a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListAttachmentsResponse containing the list of attachments.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListAttachmentsResponse listAttachments(String emailId, RequestOptions requestOptions) throws ResendException {
        return execute("/emails/receiving/" + emailId + "/attachments", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAttachmentsResponse.class);
    }

    /**
     * Retrieves a paginated list of attachments for a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @param params The params used to customize the list.
     * @return A ListAttachmentsResponse containing the paginated list of attachments.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListAttachmentsResponse listAttachments(String emailId, ListParams params) throws ResendException {
        return listAttachments(emailId, params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of attachments for a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @param params The params used to customize the list.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListAttachmentsResponse containing the paginated list of attachments.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListAttachmentsResponse listAttachments(String emailId, ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/emails/receiving/" + emailId + "/attachments" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAttachmentsResponse.class);
    }
}
