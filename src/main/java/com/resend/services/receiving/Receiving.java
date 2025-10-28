package com.resend.services.receiving;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
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
     * Retrieves a single received email by its ID.
     *
     * @param emailId The unique identifier of the received email.
     * @return The retrieved received email.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public ReceivedEmail get(String emailId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform(
                "/emails/receiving/" + emailId,
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json")
        );

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ReceivedEmail.class);
    }

    /**
     * Retrieves a list of all received emails.
     *
     * @return A ListReceivedEmailsResponse containing the list of received emails.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListReceivedEmailsResponse list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform(
                "/emails/receiving",
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json")
        );

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListReceivedEmailsResponse.class);
    }

    /**
     * Retrieves a paginated list of received emails.
     *
     * @param params The params used to customize the list.
     * @return A ListReceivedEmailsResponse containing the paginated list of received emails.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListReceivedEmailsResponse list(ListParams params) throws ResendException {
        String pathWithQuery = "/emails/receiving" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(
                pathWithQuery,
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json")
        );

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListReceivedEmailsResponse.class);
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
        AbstractHttpResponse<String> response = this.httpClient.perform(
                "/emails/receiving/" + emailId + "/attachments/" + attachmentId,
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json")
        );

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, AttachmentDetails.class);
    }

    /**
     * Retrieves a list of all attachments for a received email.
     *
     * @param emailId The unique identifier of the received email.
     * @return A ListAttachmentsResponse containing the list of attachments.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public ListAttachmentsResponse listAttachments(String emailId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform(
                "/emails/receiving/" + emailId + "/attachments",
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json")
        );

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListAttachmentsResponse.class);
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
        String pathWithQuery = "/emails/receiving/" + emailId + "/attachments" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(
                pathWithQuery,
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json")
        );

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListAttachmentsResponse.class);
    }
}
