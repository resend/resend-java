package com.resend.services.webhooks;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.webhooks.model.CreateWebhookOptions;
import com.resend.services.webhooks.model.CreateWebhookResponseSuccess;
import com.resend.services.webhooks.model.ListWebhooksResponseSuccess;
import com.resend.services.webhooks.model.RemoveWebhookResponseSuccess;
import com.resend.services.webhooks.model.UpdateWebhookOptions;
import com.resend.services.webhooks.model.UpdateWebhookResponseSuccess;
import com.resend.services.webhooks.model.GetWebhookResponseSuccess;
import com.resend.services.webhooks.model.VerifyWebhookOptions;
import okhttp3.MediaType;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Represents the Resend Webhooks module.
 */
public final class Webhooks extends BaseService {

    /**
     * Constructs an instance of the {@code Webhooks} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Webhooks(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a webhook based on the provided CreateWebhookOptions and returns a CreateWebhookResponseSuccess.
     *
     * @param createWebhookOptions The request object containing the webhook creation details.
     * @return A CreateWebhookResponseSuccess representing the result of the webhook creation operation.
     * @throws ResendException If an error occurs during the webhook creation process.
     */
    public CreateWebhookResponseSuccess create(CreateWebhookOptions createWebhookOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createWebhookOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateWebhookResponseSuccess.class);
    }

    /**
     * Updates a webhook based on the provided webhook ID and UpdateWebhookOptions, and returns an UpdateWebhookResponseSuccess.
     *
     * @param webhookId The unique identifier of the webhook to update.
     * @param updateWebhookOptions The object containing the information to be updated.
     * @return An UpdateWebhookResponseSuccess representing the result of the webhook update operation.
     * @throws ResendException If an error occurs during the webhook update process.
     */
    public UpdateWebhookResponseSuccess update(String webhookId, UpdateWebhookOptions updateWebhookOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateWebhookOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks/" + webhookId, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateWebhookResponseSuccess.class);
    }

    /**
     * Retrieves a webhook based on the provided webhook ID and returns a Webhook object.
     *
     * @param webhookId The unique identifier of the webhook to retrieve.
     * @return A Webhook object representing the retrieved webhook.
     * @throws ResendException If an error occurs during the webhook retrieval process.
     */
    public GetWebhookResponseSuccess get(String webhookId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks/" + webhookId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, GetWebhookResponseSuccess.class);
    }

    /**
     * Retrieves a list of webhooks and returns a ListWebhooksResponseSuccess.
     *
     * @return A ListWebhooksResponseSuccess containing the list of webhooks.
     * @throws ResendException If an error occurs during the webhook list retrieval process.
     */
    public ListWebhooksResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/webhooks", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListWebhooksResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of webhooks and returns a ListWebhooksResponseSuccess.
     *
     * @param params The params used to customize the list.
     * @return A ListWebhooksResponseSuccess containing the paginated list of webhooks.
     * @throws ResendException If an error occurs during the webhook list retrieval process.
     */
    public ListWebhooksResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/webhooks" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListWebhooksResponseSuccess.class);
    }

    /**
     * Deletes a webhook based on the provided webhook ID and returns a RemoveWebhookResponseSuccess.
     *
     * @param webhookId The unique identifier of the webhook to delete.
     * @return A RemoveWebhookResponseSuccess representing the result of the webhook deletion operation.
     * @throws ResendException If an error occurs during the webhook deletion process.
     */
    public RemoveWebhookResponseSuccess remove(String webhookId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks/" + webhookId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, RemoveWebhookResponseSuccess.class);
    }

    /**
     * Verifies the signature of a webhook request to ensure it was sent by Resend.
     * This method validates both the HMAC-SHA256 signature and the timestamp to prevent
     * replay attacks.
     *
     * @param options The verification options containing payload, headers, and secret.
     * @throws ResendException If the signature is invalid or the timestamp is outside the tolerance window.
     */
    public void verify(VerifyWebhookOptions options) throws ResendException {
        if (options == null) {
            throw new ResendException(400, "VerifyWebhookOptions cannot be null");
        }

        if (options.getPayload() == null || options.getPayload().isEmpty()) {
            throw new ResendException(400, "Webhook payload cannot be null or empty");
        }

        if (options.getHeaders() == null) {
            throw new ResendException(400, "Webhook headers cannot be null");
        }

        if (options.getSecret() == null || options.getSecret().isEmpty()) {
            throw new ResendException(400, "Webhook secret cannot be null or empty");
        }

        String id = options.getHeaders().get("svix-id");
        String timestamp = options.getHeaders().get("svix-timestamp");
        String signature = options.getHeaders().get("svix-signature");

        if (id == null || id.isEmpty()) {
            throw new ResendException(400, "Webhook ID (svix-id) cannot be null or empty");
        }

        if (timestamp == null || timestamp.isEmpty()) {
            throw new ResendException(400, "Webhook timestamp (svix-timestamp) cannot be null or empty");
        }

        if (signature == null || signature.isEmpty()) {
            throw new ResendException(400, "Webhook signature (svix-signature) cannot be null or empty");
        }

        // Validate timestamp (within 5 minutes tolerance)
        try {
            long webhookTimestamp = Long.parseLong(timestamp);
            long currentTimestamp = System.currentTimeMillis() / 1000;
            long timeDifference = Math.abs(currentTimestamp - webhookTimestamp);

            if (timeDifference > 300) { // 5 minutes in seconds
                throw new ResendException(400, "Webhook timestamp is outside the tolerance window (5 minutes)");
            }
        } catch (NumberFormatException e) {
            throw new ResendException(400, "Invalid webhook timestamp format");
        }

        // Extract the secret key (remove "whsec_" prefix)
        String secretKey = options.getSecret();
        if (secretKey.startsWith("whsec_")) {
            secretKey = secretKey.substring(6);
        }

        try {
            // Decode the base64 secret
            byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

            // Create the signed content: {id}.{timestamp}.{payload}
            String signedContent = id + "." + timestamp + "." + options.getPayload();

            // Generate HMAC-SHA256 signature
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
            hmac.init(secretKeySpec);
            byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));

            // Encode to base64
            String expectedSignature = Base64.getEncoder().encodeToString(hash);

            // Parse the signature header (format: "v1,signature1 v1,signature2")
            String[] signatureParts = signature.split(" ");
            boolean signatureMatches = false;

            for (String signaturePart : signatureParts) {
                String[] versionAndSignature = signaturePart.split(",", 2);
                if (versionAndSignature.length == 2) {
                    String version = versionAndSignature[0];
                    String sig = versionAndSignature[1];

                    // Only support v1 for now
                    if ("v1".equals(version)) {
                        if (constantTimeEquals(expectedSignature, sig)) {
                            signatureMatches = true;
                            break;
                        }
                    }
                }
            }

            if (!signatureMatches) {
                throw new ResendException(401, "Webhook signature verification failed");
            }

        } catch (ResendException e) {
            throw e;
        } catch (Exception e) {
            throw new ResendException(500, "Error verifying webhook signature: " + e.getMessage());
        }
    }

    /**
     * Constant-time string comparison to prevent timing attacks.
     *
     * @param a First string to compare.
     * @param b Second string to compare.
     * @return true if the strings are equal, false otherwise.
     */
    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        byte[] aBytes = a.getBytes();
        byte[] bBytes = b.getBytes();

        if (aBytes.length != bBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < aBytes.length; i++) {
            result |= aBytes[i] ^ bBytes[i];
        }

        return result == 0;
    }
}
