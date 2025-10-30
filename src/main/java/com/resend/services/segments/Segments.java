package com.resend.services.segments;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.segments.model.*;
import okhttp3.MediaType;

/**
 *  Represents the Resend Segments module.
 */
public class Segments extends BaseService {

    /**
     * Constructs an instance of the {@code Segments} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Segments(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a Segment.
     *
     * @param createSegmentOptions The Segment details.
     * @return The details of the created segment.
     * @throws ResendException If an error occurs during the Segment creation process.
     */
    public CreateSegmentResponseSuccess create(CreateSegmentOptions createSegmentOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createSegmentOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/audiences", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateSegmentResponseSuccess.class);
    }

    /**
     * Retrieves a list of segments and returns a ListSegmentsResponseSuccess.
     *
     * @return A ListSegmentsResponseSuccess containing the list of segments.
     * @throws ResendException If an error occurs during the segments list retrieval process.
     */
    public ListSegmentsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListSegmentsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of segments and returns a ListSegmentsResponseSuccess.
     * @param params The params used to customize the list.
     *
     * @return A ListSegmentsResponseSuccess containing the paginated list of segments.
     * @throws ResendException If an error occurs during the segments list retrieval process.
     */
    public ListSegmentsResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/audiences" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListSegmentsResponseSuccess.class);
    }

    /**
     * Retrieves a segment by its unique identifier.
     *
     * @param id The unique identifier of the segment.
     * @return The retrieved segment details.
     * @throws ResendException If an error occurs while retrieving the segment.
     */
    public GetSegmentResponseSuccess get(String id) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences/" +id, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetSegmentResponseSuccess.class);
    }

    /**
     * Deletes a segment based on the provided segment ID.
     *
     * @param id The unique identifier of the segment to delete.
     * @return The RemoveSegmentResponseSuccess with the details of the removed segment.
     * @throws ResendException If an error occurs during the segment deletion process.
     */
    public RemoveSegmentResponseSuccess remove(String id) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" +id, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveSegmentResponseSuccess.class);
    }
}
