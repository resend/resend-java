package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.inboxes.model.CreateInboxDraftOptions;
import com.resend.services.inboxes.model.CreateInboxDraftResponseSuccess;
import com.resend.services.inboxes.model.GetInboxDraftResponseSuccess;
import com.resend.services.inboxes.model.ListInboxDraftsResponseSuccess;
import com.resend.services.inboxes.model.RemoveInboxDraftResponseSuccess;
import com.resend.services.inboxes.model.SendInboxDraftOptions;
import com.resend.services.inboxes.model.SendInboxDraftResponseSuccess;
import com.resend.services.inboxes.model.UpdateInboxDraftOptions;
import com.resend.services.inboxes.model.UpdateInboxDraftResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Inbox Drafts module.
 */
public class InboxDrafts extends BaseService {

    /**
     * Constructs an instance of the {@code InboxDrafts} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public InboxDrafts(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code InboxDrafts} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public InboxDrafts(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves the unsent drafts on an inbox, most recently updated first.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @return A ListInboxDraftsResponseSuccess containing the drafts.
     * @throws ResendException If an error occurs while retrieving the drafts.
     */
    public ListInboxDraftsResponseSuccess list(String inboxId) throws ResendException {
        return list(inboxId, null);
    }

    /**
     * Retrieves a page of the unsent drafts on an inbox, most recently updated first.
     * The {@code after} and {@code before} cursors take a draft ID from the current page.
     * A cursor that is not an unsent draft on this inbox returns a 404.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param params  The params used to paginate the list.
     * @return A ListInboxDraftsResponseSuccess containing the drafts.
     * @throws ResendException If an error occurs while retrieving the drafts.
     */
    public ListInboxDraftsResponseSuccess list(String inboxId, ListParams params) throws ResendException {
        return execute("/inboxes/" + inboxId + "/drafts" + URLHelper.parse(params), HttpMethod.GET, null, MediaType.get("application/json"), ListInboxDraftsResponseSuccess.class);
    }

    /**
     * Creates an unsent draft on an inbox. Omit {@code thread_id} and {@code reply_to_email_id} for a draft that
     * starts a new thread. Pass both together for a reply draft in an existing thread. Only one unsent reply draft
     * can target an email, so a reply draft request for an email that already has one returns the ID of the
     * existing draft. That request ignores every other field, except {@code attachments}, which replaces the
     * attachments of the existing draft when sent. A reply draft created without {@code subject} stores an empty
     * subject and gets no {@code Re:} prefix, so set one before you send it.
     *
     * @param inboxId                 The inbox ID or the inbox email address.
     * @param createInboxDraftOptions The draft content.
     * @return The ID of the draft.
     * @throws ResendException If an error occurs while creating the draft.
     */
    public CreateInboxDraftResponseSuccess create(String inboxId, CreateInboxDraftOptions createInboxDraftOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createInboxDraftOptions);
        return execute("/inboxes/" + inboxId + "/drafts", HttpMethod.POST, payload, MediaType.get("application/json"), CreateInboxDraftResponseSuccess.class);
    }

    /**
     * Creates an unsent draft on an inbox. Omit {@code thread_id} and {@code reply_to_email_id} for a draft that
     * starts a new thread. Pass both together for a reply draft in an existing thread. Only one unsent reply draft
     * can target an email, so a reply draft request for an email that already has one returns the ID of the
     * existing draft. That request ignores every other field, except {@code attachments}, which replaces the
     * attachments of the existing draft when sent. A reply draft created without {@code subject} stores an empty
     * subject and gets no {@code Re:} prefix, so set one before you send it.
     *
     * <p>Set an idempotency key in {@code requestOptions} so the draft is created only once. A retry with the same key
     * within 24 hours returns the first successful response, with the {@code idempotent-replayed: true} header. Reusing the
     * key with a different body, or while the first request is still running, returns a 409. A failed request is
     * not stored, so you can retry it with the same key.</p>
     *
     * @param inboxId                 The inbox ID or the inbox email address.
     * @param createInboxDraftOptions The draft content.
     * @param requestOptions The options with additional headers.
     * @return The ID of the draft.
     * @throws ResendException If an error occurs while creating the draft.
     */
    public CreateInboxDraftResponseSuccess create(String inboxId, CreateInboxDraftOptions createInboxDraftOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createInboxDraftOptions);
        return execute("/inboxes/" + inboxId + "/drafts", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateInboxDraftResponseSuccess.class);
    }

    /**
     * Retrieves a single draft.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param draftId The draft ID.
     * @return The draft.
     * @throws ResendException If an error occurs while retrieving the draft.
     */
    public GetInboxDraftResponseSuccess get(String inboxId, String draftId) throws ResendException {
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId, HttpMethod.GET, null, MediaType.get("application/json"), GetInboxDraftResponseSuccess.class);
    }

    /**
     * Updates the content of an unsent draft. Omitted fields are left unchanged. A request that sets
     * {@code text} or {@code html} replaces the whole body.
     * Sending {@code attachments} replaces every attachment on the draft, and an empty list removes them all. The body part you omit from that request becomes
     * {@code null}. A draft that was already sent, or that changed during the request, returns a 409.
     *
     * @param inboxId                 The inbox ID or the inbox email address.
     * @param draftId                 The draft ID.
     * @param updateInboxDraftOptions The fields to update.
     * @return The ID of the updated draft. Use {@link #get(String, String)} to read its content.
     * @throws ResendException If an error occurs while updating the draft.
     */
    public UpdateInboxDraftResponseSuccess update(String inboxId, String draftId, UpdateInboxDraftOptions updateInboxDraftOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateInboxDraftOptions);
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId, HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateInboxDraftResponseSuccess.class);
    }

    /**
     * Discards an unsent draft. A draft that was already sent, or that changed during the request, returns a 409.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param draftId The draft ID.
     * @return The RemoveInboxDraftResponseSuccess with the details of the removed draft.
     * @throws ResendException If an error occurs while removing the draft.
     */
    public RemoveInboxDraftResponseSuccess remove(String inboxId, String draftId) throws ResendException {
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId, HttpMethod.DELETE, null, MediaType.get("application/json"), RemoveInboxDraftResponseSuccess.class);
    }

    /**
     * Sends a draft from the inbox address. A reply draft is sent in its thread. A draft without a thread starts
     * a new thread. The draft must have at least one {@code to} recipient and {@code html} or {@code text}, or the
     * request returns a 422. A draft that starts a new thread also needs a non-empty {@code subject}. A reply draft
     * can be sent with an empty subject.
     * A draft that was already sent returns a 409.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param draftId The draft ID.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the draft.
     */
    public SendInboxDraftResponseSuccess send(String inboxId, String draftId) throws ResendException {
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId + "/send", HttpMethod.POST, "", MediaType.get("application/json"), SendInboxDraftResponseSuccess.class);
    }

    /**
     * Sends a draft from the inbox address. A reply draft is sent in its thread. A draft without a thread starts
     * a new thread. The draft must have at least one {@code to} recipient and {@code html} or {@code text}, or the
     * request returns a 422. A draft that starts a new thread also needs a non-empty {@code subject}. A reply draft
     * can be sent with an empty subject.
     * A draft that was already sent returns a 409.
     *
     * <p>Set an idempotency key in {@code requestOptions} so the draft is sent only once. A retry with the same key
     * within 24 hours returns the first successful response, with the {@code idempotent-replayed: true} header. Reusing the
     * key with a different body, or while the first request is still running, returns a 409. A failed request is
     * not stored, so you can retry it with the same key.</p>
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param draftId The draft ID.
     * @param requestOptions The options with additional headers.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the draft.
     */
    public SendInboxDraftResponseSuccess send(String inboxId, String draftId, RequestOptions requestOptions) throws ResendException {
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId + "/send", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, SendInboxDraftResponseSuccess.class);
    }

    /**
     * Sends a draft from the inbox address with custom headers. A reply draft is sent in its thread. A draft
     * without a thread starts a new thread. The draft must have at least one {@code to} recipient and {@code html}
     * or {@code text}, or the request returns a 422. A draft that starts a new thread also needs a non-empty
     * {@code subject}. A reply draft can be sent with an empty subject.
     * A draft that was already sent returns a 409.
     *
     * @param inboxId               The inbox ID or the inbox email address.
     * @param draftId               The draft ID.
     * @param sendInboxDraftOptions The custom headers to add to the email.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the draft.
     */
    public SendInboxDraftResponseSuccess send(String inboxId, String draftId, SendInboxDraftOptions sendInboxDraftOptions) throws ResendException {
        String payload = sendInboxDraftOptions == null ? "" : super.resendMapper.writeValue(sendInboxDraftOptions);
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId + "/send", HttpMethod.POST, payload, MediaType.get("application/json"), SendInboxDraftResponseSuccess.class);
    }

    /**
     * Sends a draft from the inbox address with custom headers. A reply draft is sent in its thread. A draft
     * without a thread starts a new thread. The draft must have at least one {@code to} recipient and {@code html}
     * or {@code text}, or the request returns a 422. A draft that starts a new thread also needs a non-empty
     * {@code subject}. A reply draft can be sent with an empty subject.
     * A draft that was already sent returns a 409.
     *
     * <p>Set an idempotency key in {@code requestOptions} so the draft is sent only once. A retry with the same key
     * within 24 hours returns the first successful response, with the {@code idempotent-replayed: true} header. Reusing the
     * key with a different body, or while the first request is still running, returns a 409. A failed request is
     * not stored, so you can retry it with the same key.</p>
     *
     * @param inboxId               The inbox ID or the inbox email address.
     * @param draftId               The draft ID.
     * @param sendInboxDraftOptions The custom headers to add to the email.
     * @param requestOptions The options with additional headers.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the draft.
     */
    public SendInboxDraftResponseSuccess send(String inboxId, String draftId, SendInboxDraftOptions sendInboxDraftOptions, RequestOptions requestOptions) throws ResendException {
        String payload = sendInboxDraftOptions == null ? "" : super.resendMapper.writeValue(sendInboxDraftOptions);
        return execute("/inboxes/" + inboxId + "/drafts/" + draftId + "/send", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, SendInboxDraftResponseSuccess.class);
    }
}
