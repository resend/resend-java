package com.resend.services.emails;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.broadcasts.model.ListBroadcastsResponseSuccess;
import com.resend.services.emails.model.*;
import okhttp3.MediaType;

import java.util.Map;

/**
 *  Represents the Resend Emails module.
 */
public final class Emails extends BaseService {

    /**
     * Constructs an instance of the {@code Emails} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Emails(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Emails} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Emails(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Sends an email based on the provided email request.
     *
     * @param createEmailOptions The request containing email details.
     * @return The response indicating the status of the email sending.
     * @throws ResendException If an error occurs while sending the email.
     */
    public CreateEmailResponse send(CreateEmailOptions createEmailOptions) throws ResendException {
        return send(createEmailOptions, (RequestOptions) null);
    }

    /**
     * Sends an email based on the provided email request.
     *hjk
     * @param createEmailOptions The request containing email details.
     * @param requestOptions The options with additional headers.
     * @return The response indicating the status of the email sending.
     * @throws ResendException If an error occurs while sending the email.
     */
    public CreateEmailResponse send(CreateEmailOptions createEmailOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createEmailOptions);

        return execute("/emails", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateEmailResponse.class);
    }

    /**
     * @deprecated Use {@link #send(CreateEmailOptions, RequestOptions)} instead.
     * Sends an email based on the provided email request.
     *
     * @param createEmailOptions The request containing email details.
     * @param requestOptions The options with additional headers.
     * @return The response indicating the status of the email sending.
     * @throws ResendException If an error occurs while sending the email.
     */
    @Deprecated
    public CreateEmailResponse send(CreateEmailOptions createEmailOptions, Map<String,String> requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createEmailOptions);

        return execute("/emails", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateEmailResponse.class);
    }

    /**
     * Retrieves an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public Email get(String emailId) throws ResendException {
        return get(emailId, (RequestOptions) null);
    }

    /**
     * Retrieves an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public Email get(String emailId, RequestOptions requestOptions) throws ResendException {
            return execute("/emails/" + emailId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, Email.class);
    }

    /**
     * Update the email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @param updateEmailOptions The new data of the email.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public UpdateEmailResponse update(String emailId, UpdateEmailOptions updateEmailOptions) throws ResendException {
        return update(emailId, updateEmailOptions, (RequestOptions) null);
    }

    /**
     * Update the email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @param updateEmailOptions The new data of the email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public UpdateEmailResponse update(String emailId, UpdateEmailOptions updateEmailOptions, RequestOptions requestOptions) throws ResendException {

        String payload = super.resendMapper.writeValue(updateEmailOptions);
        return execute("/emails/" + emailId, HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateEmailResponse.class);
    }

    /**
     * Cancels an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public CancelEmailResponse cancel(String emailId) throws ResendException {
        return cancel(emailId, (RequestOptions) null);
    }

    /**
     * Cancels an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public CancelEmailResponse cancel(String emailId, RequestOptions requestOptions) throws ResendException {

        return execute("/emails/" + emailId + "/cancel", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, CancelEmailResponse.class);
    }

    /**
     * Creates a shareable link for an email, using the default expiration.
     *
     * @param emailId The unique identifier of the email.
     * @return The share link details.
     * @throws ResendException If an error occurs while creating the shareable link.
     */
    public ShareEmailResponse share(String emailId) throws ResendException {
        return share(emailId, (RequestOptions) null);
    }

    /**
     * Creates a shareable link for an email, using the default expiration.
     *
     * @param emailId The unique identifier of the email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The share link details.
     * @throws ResendException If an error occurs while creating the shareable link.
     */
    public ShareEmailResponse share(String emailId, RequestOptions requestOptions) throws ResendException {

        return execute("/emails/" + emailId + "/share", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, ShareEmailResponse.class);
    }

    /**
     * Creates a shareable link for an email.
     *
     * @param emailId The unique identifier of the email.
     * @param shareEmailOptions The options for the shareable link, such as its expiration.
     * @return The share link details.
     * @throws ResendException If an error occurs while creating the shareable link.
     */
    public ShareEmailResponse share(String emailId, ShareEmailOptions shareEmailOptions) throws ResendException {
        return share(emailId, shareEmailOptions, (RequestOptions) null);
    }

    /**
     * Creates a shareable link for an email.
     *
     * @param emailId The unique identifier of the email.
     * @param shareEmailOptions The options for the shareable link, such as its expiration.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The share link details.
     * @throws ResendException If an error occurs while creating the shareable link.
     */
    public ShareEmailResponse share(String emailId, ShareEmailOptions shareEmailOptions, RequestOptions requestOptions) throws ResendException {

        String payload = super.resendMapper.writeValue(shareEmailOptions);
        return execute("/emails/" + emailId + "/share", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, ShareEmailResponse.class);
    }

    /**
     * Retrieves a list of emails and returns a List.
     *
     * @return A ListEmailsResponseSuccess containing the list of emails.
     * @throws ResendException If an error occurs during the emails list retrieval process.
     */
    public ListEmailsResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of emails and returns a List.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListEmailsResponseSuccess containing the list of emails.
     * @throws ResendException If an error occurs during the emails list retrieval process.
     */
    public ListEmailsResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/emails", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListEmailsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of emails and returns a List.
     *
     * @param params The params used to customize the list.
     * @return A ListEmailsResponseSuccess containing the paginated list of emails.
     * @throws ResendException If an error occurs during the emails list retrieval process.
     */
    public ListEmailsResponseSuccess list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of emails and returns a List.
     *
     * @param params The params used to customize the list.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListEmailsResponseSuccess containing the paginated list of emails.
     * @throws ResendException If an error occurs during the emails list retrieval process.
     */
    public ListEmailsResponseSuccess list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/emails" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListEmailsResponseSuccess.class);
    }

    /**
     * Retrieves a single attachment from a sent email.
     *
     * @param emailId The unique identifier of the email.
     * @param attachmentId The unique identifier of the attachment.
     * @return The attachment details including download URL.
     * @throws ResendException If an error occurs while retrieving the attachment.
     */
    public AttachmentResponse getAttachment(String emailId, String attachmentId) throws ResendException {
        return getAttachment(emailId, attachmentId, (RequestOptions) null);
    }

    /**
     * Retrieves a single attachment from a sent email.
     *
     * @param emailId The unique identifier of the email.
     * @param attachmentId The unique identifier of the attachment.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The attachment details including download URL.
     * @throws ResendException If an error occurs while retrieving the attachment.
     */
    public AttachmentResponse getAttachment(String emailId, String attachmentId, RequestOptions requestOptions) throws ResendException {
        return execute("/emails/" + emailId + "/attachments/" + attachmentId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, AttachmentResponse.class);
    }

    /**
     * Retrieves all attachments from a sent email.
     *
     * @param emailId The unique identifier of the email.
     * @return A ListAttachmentsResponse containing all attachments from the email.
     * @throws ResendException If an error occurs while retrieving the attachments.
     */
    public ListAttachmentsResponse listAttachments(String emailId) throws ResendException {
        return listAttachments(emailId, (RequestOptions) null);
    }

    /**
     * Retrieves all attachments from a sent email.
     *
     * @param emailId The unique identifier of the email.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListAttachmentsResponse containing all attachments from the email.
     * @throws ResendException If an error occurs while retrieving the attachments.
     */
    public ListAttachmentsResponse listAttachments(String emailId, RequestOptions requestOptions) throws ResendException {
        return execute("/emails/" + emailId + "/attachments", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAttachmentsResponse.class);
    }

    /**
     * Retrieves a paginated list of attachments from a sent email.
     *
     * @param emailId The unique identifier of the email.
     * @param params The params used to customize the list (pagination).
     * @return A ListAttachmentsResponse containing the paginated list of attachments.
     * @throws ResendException If an error occurs while retrieving the attachments.
     */
    public ListAttachmentsResponse listAttachments(String emailId, ListParams params) throws ResendException {
        return listAttachments(emailId, params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of attachments from a sent email.
     *
     * @param emailId The unique identifier of the email.
     * @param params The params used to customize the list (pagination).
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListAttachmentsResponse containing the paginated list of attachments.
     * @throws ResendException If an error occurs while retrieving the attachments.
     */
    public ListAttachmentsResponse listAttachments(String emailId, ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/emails/" + emailId + "/attachments" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAttachmentsResponse.class);
    }

    /**
     * Retrieves aggregate emails metrics (received, delivered, opened, etc.) across the
     * account, for the default date range and with no breakdown by dimension.
     *
     * @return The emails metrics.
     * @throws ResendException If an error occurs while retrieving the metrics.
     */
    public EmailsMetricsResponse metrics() throws ResendException {
        return metrics(null);
    }

    /**
     * Retrieves aggregate emails metrics (received, delivered, opened, etc.) across the
     * account, filtered and broken down according to the given options.
     *
     * @param options The metrics query options; can be null.
     * @return The emails metrics.
     * @throws ResendException If an error occurs while retrieving the metrics.
     */
    public EmailsMetricsResponse metrics(GetEmailsMetricsOptions options) throws ResendException {
        return metrics(options, (RequestOptions) null);
    }

    /**
     * Retrieves aggregate emails metrics (received, delivered, opened, etc.) across the
     * account, filtered and broken down according to the given options.
     *
     * @param options The metrics query options; pass {@code null} for the default query, as in
     *                {@code metrics(null, requestOptions)}.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The emails metrics.
     * @throws ResendException If an error occurs while retrieving the metrics.
     */
    public EmailsMetricsResponse metrics(GetEmailsMetricsOptions options, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/emails/metrics" + (options == null ? "" : options.toQueryString());
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, EmailsMetricsResponse.class);
    }
}