package com.resend.services.audiences;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.audiences.model.*;
import okhttp3.MediaType;
import org.jspecify.annotations.Nullable;

/**
 *  Represents the Resend Audiences module.
 */
public class Audiences extends BaseService {

    /**
     * Constructs an instance of the {@code Audiences} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Audiences(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Audiences} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Audiences(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates an Audience.
     *
     * @param createAudienceOptions The Audience details.
     * @return The details of the created audience.
     * @throws ResendException If an error occurs during the Audience creation process.
     * @deprecated Use {@link com.resend.services.segments.Segments#create(com.resend.services.segments.model.CreateSegmentOptions)} instead.
     */
    @Deprecated
    public CreateAudienceResponseSuccess create(CreateAudienceOptions createAudienceOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createAudienceOptions);
        return execute("/audiences", HttpMethod.POST, payload, MediaType.get("application/json"), CreateAudienceResponseSuccess.class);
    }

    /**
     * Retrieves a list of audiences and returns a ListAudiencesResponseSuccess.
     *
     * @return A ListAudiencesResponseSuccess containing the list of audiences.
     * @throws ResendException If an error occurs during the audiences list retrieval process.
     * @deprecated Use {@link com.resend.services.segments.Segments#list()} instead.
     */
    @Deprecated
    public ListAudiencesResponseSuccess list() throws ResendException {
        return execute("/audiences", HttpMethod.GET, null, MediaType.get("application/json"), ListAudiencesResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of audiences and returns a ListAudiencesResponseSuccess.
     * @param params The params used to customize the list.
     *
     * @return A ListAudiencesResponseSuccess containing the paginated list of audiences.
     * @throws ResendException If an error occurs during the audiences list retrieval process.
     * @deprecated Use {@link com.resend.services.segments.Segments#list(ListParams)} instead.
     */
    @Deprecated
    public ListAudiencesResponseSuccess list(@Nullable ListParams params) throws ResendException {
        String pathWithQuery = "/audiences" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListAudiencesResponseSuccess.class);
    }

    /**
     * Retrieves a audience by its unique identifier.
     *
     * @param id The unique identifier of the audience.
     * @return The retrieved audience details.
     * @throws ResendException If an error occurs while retrieving the audience.
     * @deprecated Use {@link com.resend.services.segments.Segments#get(String)} instead.
     */
    @Deprecated
    public GetAudienceResponseSuccess get(String id) throws ResendException {
        return execute("/audiences/" +id, HttpMethod.GET, null, MediaType.get("application/json"), GetAudienceResponseSuccess.class);
    }

    /**
     * Deletes an audience based on the provided audience ID.
     *
     * @param id The unique identifier of the audience to delete.
     * @return The RemoveAudiencesResponseSuccess with the details of the removed audience.
     * @throws ResendException If an error occurs during the audience deletion process.
     * @deprecated Use {@link com.resend.services.segments.Segments#remove(String)} instead.
     */
    @Deprecated
    public RemoveAudienceResponseSuccess remove(String id) throws ResendException {
        return execute("/audiences/" +id, HttpMethod.DELETE, "", null, RemoveAudienceResponseSuccess.class);
    }
}