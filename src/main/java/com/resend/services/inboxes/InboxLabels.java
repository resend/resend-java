package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.inboxes.model.CreateInboxLabelOptions;
import com.resend.services.inboxes.model.CreateInboxLabelResponseSuccess;
import com.resend.services.inboxes.model.ListInboxLabelsResponseSuccess;
import com.resend.services.inboxes.model.RemoveInboxLabelResponseSuccess;
import com.resend.services.inboxes.model.UpdateInboxLabelOptions;
import com.resend.services.inboxes.model.UpdateInboxLabelResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Inbox Labels module.
 */
public class InboxLabels extends BaseService {

    /**
     * Constructs an instance of the {@code InboxLabels} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public InboxLabels(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code InboxLabels} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public InboxLabels(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves every label on an inbox, oldest first. The list is not paginated.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @return A ListInboxLabelsResponseSuccess containing the labels.
     * @throws ResendException If an error occurs while retrieving the labels.
     */
    public ListInboxLabelsResponseSuccess list(String inboxId) throws ResendException {
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/labels", HttpMethod.GET, null, MediaType.get("application/json"), ListInboxLabelsResponseSuccess.class);
    }

    /**
     * Creates a label on an inbox. An inbox can have at most 100 labels. Label names are unique per inbox,
     * compared without case. A duplicate name or a full inbox returns a 422.
     *
     * @param inboxId                 The inbox ID or the inbox email address.
     * @param createInboxLabelOptions The label details.
     * @return The ID of the created label.
     * @throws ResendException If an error occurs while creating the label.
     */
    public CreateInboxLabelResponseSuccess create(String inboxId, CreateInboxLabelOptions createInboxLabelOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createInboxLabelOptions);
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/labels", HttpMethod.POST, payload, MediaType.get("application/json"), CreateInboxLabelResponseSuccess.class);
    }

    /**
     * Renames or recolors a label. Omitted fields keep their current value. A new name must not match another
     * label on the inbox, compared without case, or the request returns a 422.
     *
     * @param inboxId                 The inbox ID or the inbox email address.
     * @param labelId                 The label ID.
     * @param updateInboxLabelOptions The fields to update.
     * @return The ID of the updated label.
     * @throws ResendException If an error occurs while updating the label.
     */
    public UpdateInboxLabelResponseSuccess update(String inboxId, String labelId, UpdateInboxLabelOptions updateInboxLabelOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateInboxLabelOptions);
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/labels/" + URLHelper.encodePathSegment(labelId), HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateInboxLabelResponseSuccess.class);
    }

    /**
     * Deletes a label and removes it from every thread that carries it.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param labelId The label ID.
     * @return The RemoveInboxLabelResponseSuccess with the details of the removed label.
     * @throws ResendException If an error occurs while removing the label.
     */
    public RemoveInboxLabelResponseSuccess remove(String inboxId, String labelId) throws ResendException {
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/labels/" + URLHelper.encodePathSegment(labelId), HttpMethod.DELETE, null, MediaType.get("application/json"), RemoveInboxLabelResponseSuccess.class);
    }
}
