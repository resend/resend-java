package com.resend.services.automations;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.service.BaseService;
import com.resend.services.automations.model.*;
import okhttp3.MediaType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Automations extends BaseService {

    public Automations(final String apiKey) {
        super(apiKey);
    }

    public CreateAutomationResponseSuccess create(CreateAutomationOptions createAutomationOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createAutomationOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/automations", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateAutomationResponseSuccess.class);
    }

    public Automation get(String automationId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/automations/" + automationId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, Automation.class);
    }

    public ListAutomationsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/automations", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListAutomationsResponseSuccess.class);
    }

    public ListAutomationsResponseSuccess list(ListAutomationsParams params) throws ResendException {
        String pathWithQuery = "/automations" + buildQueryString(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListAutomationsResponseSuccess.class);
    }

    public UpdateAutomationResponseSuccess update(UpdateAutomationOptions updateAutomationOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateAutomationOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/automations/" + updateAutomationOptions.getId(), super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateAutomationResponseSuccess.class);
    }

    public DeleteAutomationResponseSuccess remove(String automationId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/automations/" + automationId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, DeleteAutomationResponseSuccess.class);
    }

    public StopAutomationResponseSuccess stop(String automationId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/automations/" + automationId + "/stop", super.apiKey, HttpMethod.POST, "", MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, StopAutomationResponseSuccess.class);
    }

    public ListAutomationRunsResponseSuccess listRuns(String automationId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/automations/" + automationId + "/runs", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListAutomationRunsResponseSuccess.class);
    }

    public ListAutomationRunsResponseSuccess listRuns(String automationId, ListAutomationRunsParams params) throws ResendException {
        String pathWithQuery = "/automations/" + automationId + "/runs" + buildRunsQueryString(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListAutomationRunsResponseSuccess.class);
    }

    public AutomationRun getRun(String automationId, String runId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/automations/" + automationId + "/runs/" + runId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, AutomationRun.class);
    }

    private String buildQueryString(ListAutomationsParams params) {
        if (params == null) {
            return "";
        }

        Map<String, String> queryParams = new LinkedHashMap<>();

        if (params.getStatus() != null) {
            queryParams.put("status", params.getStatus().getValue());
        }
        if (params.getLimit() != null) {
            queryParams.put("limit", params.getLimit().toString());
        }
        if (params.getAfter() != null && !params.getAfter().isEmpty()) {
            queryParams.put("after", params.getAfter());
        }
        if (params.getBefore() != null && !params.getBefore().isEmpty()) {
            queryParams.put("before", params.getBefore());
        }

        if (queryParams.isEmpty()) {
            return "";
        }

        String query = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        return "?" + query;
    }

    private String buildRunsQueryString(ListAutomationRunsParams params) {
        if (params == null) {
            return "";
        }

        Map<String, String> queryParams = new LinkedHashMap<>();

        if (params.getStatus() != null) {
            queryParams.put("status", params.getStatus().getValue());
        }
        if (params.getLimit() != null) {
            queryParams.put("limit", params.getLimit().toString());
        }
        if (params.getAfter() != null && !params.getAfter().isEmpty()) {
            queryParams.put("after", params.getAfter());
        }
        if (params.getBefore() != null && !params.getBefore().isEmpty()) {
            queryParams.put("before", params.getBefore());
        }

        if (queryParams.isEmpty()) {
            return "";
        }

        String query = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        return "?" + query;
    }
}
