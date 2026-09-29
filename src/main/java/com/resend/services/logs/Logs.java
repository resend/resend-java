package com.resend.services.logs;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.logs.model.GetLogResponseSuccess;
import com.resend.services.logs.model.ListLogsResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Logs module.
 */
public class Logs extends BaseService {

    /**
     * Constructs an instance of the {@code Logs} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Logs(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Logs} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Logs(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves a single log entry by its unique identifier.
     *
     * @param logId The unique identifier of the log.
     * @return The retrieved log details.
     * @throws ResendException If an error occurs while retrieving the log.
     */
    public GetLogResponseSuccess get(String logId) throws ResendException {
        return execute("/logs/" + logId, HttpMethod.GET, null, MediaType.get("application/json"), GetLogResponseSuccess.class);
    }

    /**
     * Retrieves a list of logs.
     *
     * @return A ListLogsResponseSuccess containing the list of logs.
     * @throws ResendException If an error occurs during the logs list retrieval process.
     */
    public ListLogsResponseSuccess list() throws ResendException {
        return execute("/logs", HttpMethod.GET, null, MediaType.get("application/json"), ListLogsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of logs.
     *
     * @param params The params used to customize the list (limit, after, before).
     * @return A ListLogsResponseSuccess containing the paginated list of logs.
     * @throws ResendException If an error occurs during the logs list retrieval process.
     */
    public ListLogsResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/logs" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListLogsResponseSuccess.class);
    }
}
