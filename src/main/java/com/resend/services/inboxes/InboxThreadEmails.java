package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.inboxes.model.ForwardInboxThreadEmailOptions;
import com.resend.services.inboxes.model.ForwardInboxThreadEmailResponseSuccess;
import com.resend.services.inboxes.model.GetInboxThreadEmailResponseSuccess;
import com.resend.services.inboxes.model.ListInboxThreadEmailsResponseSuccess;
import com.resend.services.inboxes.model.ReplyInboxThreadEmailOptions;
import com.resend.services.inboxes.model.ReplyInboxThreadEmailResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Inbox Thread Emails module.
 */
public class InboxThreadEmails extends BaseService {

    /**
     * Constructs an instance of the {@code InboxThreadEmails} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public InboxThreadEmails(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code InboxThreadEmails} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public InboxThreadEmails(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves the messages of a thread, oldest first.
     *
     * @param inboxId  The inbox ID. An email address is not accepted.
     * @param threadId The thread ID.
     * @return A ListInboxThreadEmailsResponseSuccess containing the messages.
     * @throws ResendException If an error occurs while retrieving the messages.
     */
    public ListInboxThreadEmailsResponseSuccess list(String inboxId, String threadId) throws ResendException {
        return list(inboxId, threadId, null);
    }

    /**
     * Retrieves a page of the messages of a thread, oldest first. Pass the {@code id} of the last message as
     * {@code after} to get the next, newer page. Pass the {@code id} of the first message as {@code before}
     * to get the previous, older page. You cannot combine {@code after} and {@code before}. A cursor that is not
     * a message of the thread returns a 404.
     *
     * @param inboxId  The inbox ID. An email address is not accepted.
     * @param threadId The thread ID.
     * @param params   The params used to paginate the list.
     * @return A ListInboxThreadEmailsResponseSuccess containing the messages.
     * @throws ResendException If an error occurs while retrieving the messages.
     */
    public ListInboxThreadEmailsResponseSuccess list(String inboxId, String threadId, ListParams params) throws ResendException {
        String path = "/inboxes/" + inboxId + "/threads/" + threadId + "/emails" + URLHelper.parse(params);
        return execute(path, HttpMethod.GET, null, MediaType.get("application/json"), ListInboxThreadEmailsResponseSuccess.class);
    }

    /**
     * Retrieves a single message of a thread.
     *
     * @param inboxId  The inbox ID. An email address is not accepted.
     * @param threadId The thread ID.
     * @param emailId  The email ID, as returned in {@code data[].id} by the list thread emails endpoint.
     * @return The message.
     * @throws ResendException If an error occurs while retrieving the message.
     */
    public GetInboxThreadEmailResponseSuccess get(String inboxId, String threadId, String emailId) throws ResendException {
        String path = "/inboxes/" + inboxId + "/threads/" + threadId + "/emails/" + emailId;
        return execute(path, HttpMethod.GET, null, MediaType.get("application/json"), GetInboxThreadEmailResponseSuccess.class);
    }

    /**
     * Sends a reply from the inbox address right away, with no stored draft. The {@code to} recipients come from
     * the message you reply to. For a message the inbox received, the reply goes to its {@code Reply-To}
     * addresses, or to its sender when it has no {@code Reply-To}. For a message the inbox sent, the reply goes
     * to that message's {@code to} recipients. The reply threads under the message through the
     * {@code In-Reply-To} and {@code References} headers.
     *
     * @param inboxId                      The inbox ID. An email address is not accepted.
     * @param threadId                     The thread ID.
     * @param emailId                      The email ID of the message to reply to.
     * @param replyInboxThreadEmailOptions The reply content.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the reply.
     */
    public ReplyInboxThreadEmailResponseSuccess reply(String inboxId, String threadId, String emailId, ReplyInboxThreadEmailOptions replyInboxThreadEmailOptions) throws ResendException {
        String path = "/inboxes/" + inboxId + "/threads/" + threadId + "/emails/" + emailId + "/reply";
        String payload = super.resendMapper.writeValue(replyInboxThreadEmailOptions);
        return execute(path, HttpMethod.POST, payload, MediaType.get("application/json"), ReplyInboxThreadEmailResponseSuccess.class);
    }

    /**
     * Sends a reply from the inbox address right away, with no stored draft. The {@code to} recipients come from
     * the message you reply to. For a message the inbox received, the reply goes to its {@code Reply-To}
     * addresses, or to its sender when it has no {@code Reply-To}. For a message the inbox sent, the reply goes
     * to that message's {@code to} recipients. The reply threads under the message through the
     * {@code In-Reply-To} and {@code References} headers.
     *
     * <p>Set an idempotency key in {@code requestOptions} so the reply is sent only once. A retry with the same key
     * within 24 hours returns the first response, with the {@code idempotent-replayed: true} header. Reusing the
     * key with a different body, or while the first request is still running, returns a 409.</p>
     *
     * @param inboxId                      The inbox ID. An email address is not accepted.
     * @param threadId                     The thread ID.
     * @param emailId                      The email ID of the message to reply to.
     * @param replyInboxThreadEmailOptions The reply content.
     * @param requestOptions The options with additional headers.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the reply.
     */
    public ReplyInboxThreadEmailResponseSuccess reply(String inboxId, String threadId, String emailId, ReplyInboxThreadEmailOptions replyInboxThreadEmailOptions, RequestOptions requestOptions) throws ResendException {
        String path = "/inboxes/" + inboxId + "/threads/" + threadId + "/emails/" + emailId + "/reply";
        String payload = super.resendMapper.writeValue(replyInboxThreadEmailOptions);
        return execute(path, HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, ReplyInboxThreadEmailResponseSuccess.class);
    }

    /**
     * Forwards a message from the inbox address right away, with no stored draft. The original message is quoted
     * under a forwarded-message banner, below the optional {@code html} or {@code text} note. The attachments of
     * the original message are sent with the forward, together with any attachments you add. The request fails
     * when they exceed 10 files or 25 MB of {@code content} in total. An original attachment that was sent from
     * a URL ({@code path}) cannot be forwarded, and the request returns a 422.
     *
     * @param inboxId                        The inbox ID. An email address is not accepted.
     * @param threadId                       The thread ID.
     * @param emailId                        The email ID of the message to forward.
     * @param forwardInboxThreadEmailOptions The forward recipients and content.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the forward.
     */
    public ForwardInboxThreadEmailResponseSuccess forward(String inboxId, String threadId, String emailId, ForwardInboxThreadEmailOptions forwardInboxThreadEmailOptions) throws ResendException {
        String path = "/inboxes/" + inboxId + "/threads/" + threadId + "/emails/" + emailId + "/forward";
        String payload = super.resendMapper.writeValue(forwardInboxThreadEmailOptions);
        return execute(path, HttpMethod.POST, payload, MediaType.get("application/json"), ForwardInboxThreadEmailResponseSuccess.class);
    }

    /**
     * Forwards a message from the inbox address right away, with no stored draft. The original message is quoted
     * under a forwarded-message banner, below the optional {@code html} or {@code text} note. The attachments of
     * the original message are sent with the forward, together with any attachments you add. The request fails
     * when they exceed 10 files or 25 MB of {@code content} in total. An original attachment that was sent from
     * a URL ({@code path}) cannot be forwarded, and the request returns a 422.
     *
     * <p>Set an idempotency key in {@code requestOptions} so the forward is sent only once. A retry with the same key
     * within 24 hours returns the first response, with the {@code idempotent-replayed: true} header. Reusing the
     * key with a different body, or while the first request is still running, returns a 409.</p>
     *
     * @param inboxId                        The inbox ID. An email address is not accepted.
     * @param threadId                       The thread ID.
     * @param emailId                        The email ID of the message to forward.
     * @param forwardInboxThreadEmailOptions The forward recipients and content.
     * @param requestOptions The options with additional headers.
     * @return The ID of the sent email.
     * @throws ResendException If an error occurs while sending the forward.
     */
    public ForwardInboxThreadEmailResponseSuccess forward(String inboxId, String threadId, String emailId, ForwardInboxThreadEmailOptions forwardInboxThreadEmailOptions, RequestOptions requestOptions) throws ResendException {
        String path = "/inboxes/" + inboxId + "/threads/" + threadId + "/emails/" + emailId + "/forward";
        String payload = super.resendMapper.writeValue(forwardInboxThreadEmailOptions);
        return execute(path, HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, ForwardInboxThreadEmailResponseSuccess.class);
    }
}
