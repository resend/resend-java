package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.inboxes.model.AddInboxThreadLabelsOptions;
import com.resend.services.inboxes.model.AddInboxThreadLabelsResponseSuccess;
import com.resend.services.inboxes.model.GetInboxThreadResponseSuccess;
import com.resend.services.inboxes.model.ListInboxThreadsParams;
import com.resend.services.inboxes.model.ListInboxThreadsResponseSuccess;
import com.resend.services.inboxes.model.RemoveInboxThreadLabelResponseSuccess;
import com.resend.services.inboxes.model.RemoveInboxThreadResponseSuccess;
import com.resend.services.inboxes.model.SearchInboxThreadsParams;
import com.resend.services.inboxes.model.SearchInboxThreadsResponseSuccess;
import com.resend.services.inboxes.model.SetInboxThreadLabelsOptions;
import com.resend.services.inboxes.model.SetInboxThreadLabelsResponseSuccess;
import com.resend.services.inboxes.model.UpdateInboxThreadOptions;
import com.resend.services.inboxes.model.UpdateInboxThreadResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Inbox Threads module.
 */
public class InboxThreads extends BaseService {

    /**
     * Constructs an instance of the {@code InboxThreads} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public InboxThreads(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code InboxThreads} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public InboxThreads(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Returns an InboxThreadEmails object that can be used to read, reply to, and forward the emails of a thread.
     *
     * @return An InboxThreadEmails object.
     */
    public InboxThreadEmails emails() {
        return new InboxThreadEmails(super.apiKey, super.httpClient);
    }

    /**
     * Retrieves the threads in an inbox, most recently active first.
     * To find threads by text, sender, recipient, attachment, or date, use {@link #search(String, SearchInboxThreadsParams)}.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @return A ListInboxThreadsResponseSuccess containing the threads.
     * @throws ResendException If an error occurs while retrieving the threads.
     */
    public ListInboxThreadsResponseSuccess list(String inboxId) throws ResendException {
        return list(inboxId, null);
    }

    /**
     * Retrieves the threads in an inbox, most recently active first, with filtering and pagination.
     * The {@code after} and {@code before} cursors take a thread ID, and only one of them can be passed.
     * A cursor thread that is not in the selected folders returns a 404.
     * To find threads by text, sender, recipient, attachment, or date, use {@link #search(String, SearchInboxThreadsParams)}.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param params  The params used to filter and paginate the list.
     * @return A ListInboxThreadsResponseSuccess containing the threads.
     * @throws ResendException If an error occurs while retrieving the threads.
     */
    public ListInboxThreadsResponseSuccess list(String inboxId, ListInboxThreadsParams params) throws ResendException {
        String query = params == null ? "" : params.toQueryString();
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads" + query, HttpMethod.GET, null, MediaType.get("application/json"), ListInboxThreadsResponseSuccess.class);
    }

    /**
     * Searches the threads of an inbox, newest activity first.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @return A SearchInboxThreadsResponseSuccess containing the matching threads.
     * @throws ResendException If an error occurs while searching the threads.
     */
    public SearchInboxThreadsResponseSuccess search(String inboxId) throws ResendException {
        return search(inboxId, null);
    }

    /**
     * Finds threads by the text of their emails, who sent or received them, whether they have attachments,
     * and their dates. Results are ordered by latest activity, newest first, and paginate like the list threads
     * endpoint. Search can be up to about a minute behind new emails. When more than 10,000 threads match,
     * search covers the 10,000 with the newest matching email. When any email filter is set, the matching email
     * must itself be in one of {@code folders}.
     *
     * @param inboxId The inbox ID or the inbox email address.
     * @param params  The search filters and pagination.
     * @return A SearchInboxThreadsResponseSuccess containing the matching threads.
     * @throws ResendException If an error occurs while searching the threads.
     */
    public SearchInboxThreadsResponseSuccess search(String inboxId, SearchInboxThreadsParams params) throws ResendException {
        String query = params == null ? "" : params.toQueryString();
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/search" + query, HttpMethod.GET, null, MediaType.get("application/json"), SearchInboxThreadsResponseSuccess.class);
    }

    /**
     * Retrieves the summary of a thread. The emails in the thread are listed with {@link InboxThreadEmails#list(String, String)}.
     *
     * @param inboxId  The inbox ID or the inbox email address.
     * @param threadId The thread ID.
     * @return The thread summary.
     * @throws ResendException If an error occurs while retrieving the thread.
     */
    public GetInboxThreadResponseSuccess get(String inboxId, String threadId) throws ResendException {
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/" + URLHelper.encodePathSegment(threadId), HttpMethod.GET, null, MediaType.get("application/json"), GetInboxThreadResponseSuccess.class);
    }

    /**
     * Marks a thread read or unread, or moves it to a folder.
     * Moving a thread out of {@code trash} restores it and clears its deletion deadline.
     * To change the labels of a thread, use {@link #addLabels}, {@link #setLabels} or {@link #removeLabel}.
     *
     * @param inboxId                  The inbox ID or the inbox email address.
     * @param threadId                 The thread ID.
     * @param updateInboxThreadOptions The changes to apply.
     * @return The updated thread summary.
     * @throws ResendException If an error occurs while updating the thread.
     */
    public UpdateInboxThreadResponseSuccess update(String inboxId, String threadId, UpdateInboxThreadOptions updateInboxThreadOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateInboxThreadOptions);
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/" + URLHelper.encodePathSegment(threadId), HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateInboxThreadResponseSuccess.class);
    }

    /**
     * Moves a thread to {@code trash} and schedules it for deletion in 30 days. This is reversible.
     * To restore the thread, update its folder to {@code inbox}, {@code archive}, or {@code spam},
     * which also clears the deletion deadline.
     *
     * @param inboxId  The inbox ID or the inbox email address.
     * @param threadId The thread ID.
     * @return The RemoveInboxThreadResponseSuccess with the details of the removed thread.
     * @throws ResendException If an error occurs while removing the thread.
     */
    public RemoveInboxThreadResponseSuccess remove(String inboxId, String threadId) throws ResendException {
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/" + URLHelper.encodePathSegment(threadId), HttpMethod.DELETE, null, MediaType.get("application/json"), RemoveInboxThreadResponseSuccess.class);
    }

    /**
     * Adds labels to a thread. Labels already on the thread stay on it. If any label ID is not found, nothing changes.
     *
     * @param inboxId                     The inbox ID or the inbox email address.
     * @param threadId                    The thread ID.
     * @param addInboxThreadLabelsOptions The IDs of the labels to add.
     * @return Every label on the thread after the change.
     * @throws ResendException If an error occurs while adding the labels.
     */
    public AddInboxThreadLabelsResponseSuccess addLabels(String inboxId, String threadId, AddInboxThreadLabelsOptions addInboxThreadLabelsOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(addInboxThreadLabelsOptions);
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/" + URLHelper.encodePathSegment(threadId) + "/labels", HttpMethod.POST, payload, MediaType.get("application/json"), AddInboxThreadLabelsResponseSuccess.class);
    }

    /**
     * Sets the labels of a thread in one atomic change. Labels on the thread that are not listed are taken off,
     * and the labels themselves are kept. An empty list takes every label off the thread.
     *
     * @param inboxId                     The inbox ID or the inbox email address.
     * @param threadId                    The thread ID.
     * @param setInboxThreadLabelsOptions The IDs of the labels the thread should carry.
     * @return Every label on the thread after the change.
     * @throws ResendException If an error occurs while setting the labels.
     */
    public SetInboxThreadLabelsResponseSuccess setLabels(String inboxId, String threadId, SetInboxThreadLabelsOptions setInboxThreadLabelsOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(setInboxThreadLabelsOptions);
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/" + URLHelper.encodePathSegment(threadId) + "/labels", HttpMethod.PUT, payload, MediaType.get("application/json"), SetInboxThreadLabelsResponseSuccess.class);
    }

    /**
     * Removes a label from a thread. The label itself is kept, and stays on every other thread that carries it.
     * To delete the label everywhere, use {@link InboxLabels#remove(String, String)}.
     *
     * @param inboxId  The inbox ID or the inbox email address.
     * @param threadId The thread ID.
     * @param labelId  The label ID.
     * @return Every label on the thread after the change.
     * @throws ResendException If an error occurs while removing the label.
     */
    public RemoveInboxThreadLabelResponseSuccess removeLabel(String inboxId, String threadId, String labelId) throws ResendException {
        return execute("/inboxes/" + URLHelper.encodePathSegment(inboxId) + "/threads/" + URLHelper.encodePathSegment(threadId) + "/labels/" + URLHelper.encodePathSegment(labelId), HttpMethod.DELETE, null, MediaType.get("application/json"), RemoveInboxThreadLabelResponseSuccess.class);
    }
}
