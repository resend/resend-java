package com.resend.services.automations;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.automations.model.*;
import okhttp3.MediaType;


/**
 * Represents the Resend Automations module.
 */
public class Automations extends BaseService {

    /**
     * Constructs an instance of the {@code Automations} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Automations(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Automations} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Automations(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a new automation.
     *
     * @param createAutomationOptions The options for creating an automation.
     * @return The response containing the created automation details.
     * @throws ResendException If an error occurs while creating the automation.
     */
    public CreateAutomationResponseSuccess create(CreateAutomationOptions createAutomationOptions) throws ResendException {
        return create(createAutomationOptions, (RequestOptions) null);
    }

    /**
     * Creates a new automation.
     *
     * @param createAutomationOptions The options for creating an automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the created automation details.
     * @throws ResendException If an error occurs while creating the automation.
     */
    public CreateAutomationResponseSuccess create(CreateAutomationOptions createAutomationOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createAutomationOptions);
        return execute("/automations", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateAutomationResponseSuccess.class);
    }

    /**
     * Retrieves an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @return The automation details.
     * @throws ResendException If an error occurs while retrieving the automation.
     */
    public Automation get(String automationId) throws ResendException {
        return get(automationId, (RequestOptions) null);
    }

    /**
     * Retrieves an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The automation details.
     * @throws ResendException If an error occurs while retrieving the automation.
     */
    public Automation get(String automationId, RequestOptions requestOptions) throws ResendException {
        return execute("/automations/" + automationId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, Automation.class);
    }

    /**
     * Lists all automations.
     *
     * @return The response containing the list of automations.
     * @throws ResendException If an error occurs while listing the automations.
     */
    public ListAutomationsResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Lists all automations.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the list of automations.
     * @throws ResendException If an error occurs while listing the automations.
     */
    public ListAutomationsResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/automations", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAutomationsResponseSuccess.class);
    }

    /**
     * Lists all automations with filtering and pagination support.
     *
     * @param params The parameters for filtering and pagination.
     * @return The response containing the list of automations.
     * @throws ResendException If an error occurs while listing the automations.
     */
    public ListAutomationsResponseSuccess list(ListAutomationsParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Lists all automations with filtering and pagination support.
     *
     * @param params The parameters for filtering and pagination.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the list of automations.
     * @throws ResendException If an error occurs while listing the automations.
     */
    public ListAutomationsResponseSuccess list(ListAutomationsParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/automations" + (params != null ? params.toQueryString() : "");
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAutomationsResponseSuccess.class);
    }

    /**
     * Updates an existing automation.
     *
     * @param updateAutomationOptions The options for updating the automation.
     * @return The response containing the updated automation details.
     * @throws ResendException If an error occurs while updating the automation.
     */
    public UpdateAutomationResponseSuccess update(UpdateAutomationOptions updateAutomationOptions) throws ResendException {
        return update(updateAutomationOptions, (RequestOptions) null);
    }

    /**
     * Updates an existing automation.
     *
     * @param updateAutomationOptions The options for updating the automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the updated automation details.
     * @throws ResendException If an error occurs while updating the automation.
     */
    public UpdateAutomationResponseSuccess update(UpdateAutomationOptions updateAutomationOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateAutomationOptions);
        return execute("/automations/" + updateAutomationOptions.getId(), HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateAutomationResponseSuccess.class);
    }

    /**
     * Removes an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @return The response indicating the automation was deleted.
     * @throws ResendException If an error occurs while removing the automation.
     */
    public DeleteAutomationResponseSuccess remove(String automationId) throws ResendException {
        return remove(automationId, (RequestOptions) null);
    }

    /**
     * Removes an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response indicating the automation was deleted.
     * @throws ResendException If an error occurs while removing the automation.
     */
    public DeleteAutomationResponseSuccess remove(String automationId, RequestOptions requestOptions) throws ResendException {
        return execute("/automations/" + automationId, HttpMethod.DELETE, "", null, requestOptions, DeleteAutomationResponseSuccess.class);
    }

    /**
     * Duplicates an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @return The response containing the newly created automation ID.
     * @throws ResendException If an error occurs while duplicating the automation.
     */
    public DuplicateAutomationResponseSuccess duplicate(String automationId) throws ResendException {
        return duplicate(automationId, (RequestOptions) null);
    }

    /**
     * Duplicates an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the newly created automation ID.
     * @throws ResendException If an error occurs while duplicating the automation.
     */
    public DuplicateAutomationResponseSuccess duplicate(String automationId, RequestOptions requestOptions) throws ResendException {
        return execute("/automations/" + automationId + "/duplicate", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, DuplicateAutomationResponseSuccess.class);
    }

    /**
     * Stops an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @return The response indicating the automation was stopped.
     * @throws ResendException If an error occurs while stopping the automation.
     */
    public StopAutomationResponseSuccess stop(String automationId) throws ResendException {
        return stop(automationId, (RequestOptions) null);
    }

    /**
     * Stops an automation by its unique identifier.
     *
     * @param automationId The unique identifier of the automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response indicating the automation was stopped.
     * @throws ResendException If an error occurs while stopping the automation.
     */
    public StopAutomationResponseSuccess stop(String automationId, RequestOptions requestOptions) throws ResendException {
        return execute("/automations/" + automationId + "/stop", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, StopAutomationResponseSuccess.class);
    }

    /**
     * Lists all runs for an automation.
     *
     * @param automationId The unique identifier of the automation.
     * @return The response containing the list of automation runs.
     * @throws ResendException If an error occurs while listing the runs.
     */
    public ListAutomationRunsResponseSuccess listRuns(String automationId) throws ResendException {
        return listRuns(automationId, (RequestOptions) null);
    }

    /**
     * Lists all runs for an automation.
     *
     * @param automationId The unique identifier of the automation.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the list of automation runs.
     * @throws ResendException If an error occurs while listing the runs.
     */
    public ListAutomationRunsResponseSuccess listRuns(String automationId, RequestOptions requestOptions) throws ResendException {
        return execute("/automations/" + automationId + "/runs", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAutomationRunsResponseSuccess.class);
    }

    /**
     * Lists all runs for an automation with filtering and pagination support.
     *
     * @param automationId The unique identifier of the automation.
     * @param params The parameters for filtering and pagination.
     * @return The response containing the list of automation runs.
     * @throws ResendException If an error occurs while listing the runs.
     */
    public ListAutomationRunsResponseSuccess listRuns(String automationId, ListAutomationRunsParams params) throws ResendException {
        return listRuns(automationId, params, (RequestOptions) null);
    }

    /**
     * Lists all runs for an automation with filtering and pagination support.
     *
     * @param automationId The unique identifier of the automation.
     * @param params The parameters for filtering and pagination.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the list of automation runs.
     * @throws ResendException If an error occurs while listing the runs.
     */
    public ListAutomationRunsResponseSuccess listRuns(String automationId, ListAutomationRunsParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/automations/" + automationId + "/runs" + (params != null ? params.toQueryString() : "");
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListAutomationRunsResponseSuccess.class);
    }

    /**
     * Retrieves a specific run for an automation.
     *
     * @param options The options containing automation ID and run ID.
     * @return The automation run details.
     * @throws ResendException If an error occurs while retrieving the run.
     */
    public AutomationRun getRun(GetAutomationRunOptions options) throws ResendException {
        return getRun(options, (RequestOptions) null);
    }

    /**
     * Retrieves a specific run for an automation.
     *
     * @param options The options containing automation ID and run ID.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The automation run details.
     * @throws ResendException If an error occurs while retrieving the run.
     */
    public AutomationRun getRun(GetAutomationRunOptions options, RequestOptions requestOptions) throws ResendException {
        if (options.getAutomationId() == null || options.getAutomationId().isEmpty()) {
            throw new IllegalArgumentException("automationId must be provided");
        }
        if (options.getRunId() == null || options.getRunId().isEmpty()) {
            throw new IllegalArgumentException("runId must be provided");
        }
        return execute("/automations/" + options.getAutomationId() + "/runs/" + options.getRunId(), HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, AutomationRun.class);
    }
}
