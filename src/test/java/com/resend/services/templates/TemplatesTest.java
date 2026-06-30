package com.resend.services.templates;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.templates.model.*;
import com.resend.services.util.TemplatesUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class TemplatesTest {

    private static final String TEMPLATE_ID = "34a080c9-b17d-4187-ad80-5af20266e535";
    private static final String CREATE_RESPONSE_JSON = "{\"id\":\"49a3999c-0ce1-4ea6-ab68-afcd6dc2e794\",\"object\":\"template\"}";
    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"" + TEMPLATE_ID + "\",\"object\":\"template\",\"alias\":\"reset-password\"," +
            "\"name\":\"reset-password\",\"status\":\"published\"," +
            "\"created_at\":\"2023-10-06T23:47:56.678Z\",\"updated_at\":\"2023-10-06T23:47:56.678Z\"," +
            "\"published_at\":\"2023-10-06T23:47:56.678Z\"," +
            "\"from\":\"John Doe <john.doe@example.com>\",\"subject\":\"Hello, world!\"," +
            "\"html\":\"<h1>Hello, world!</h1>\",\"text\":\"Hello, world!\",\"variables\":[]}";
    private static final String LIST_RESPONSE_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\",\"name\":\"reset-password\",\"status\":\"draft\"," +
            "\"alias\":\"reset-password\",\"created_at\":\"2023-10-06T23:47:56.678Z\",\"updated_at\":\"2023-10-06T23:47:56.678Z\"}," +
            "{\"id\":\"b7f9c2e1-1234-4abc-9def-567890abcdef\",\"name\":\"welcome-message\",\"status\":\"published\"," +
            "\"alias\":\"welcome-message\",\"created_at\":\"2023-10-06T23:47:56.678Z\",\"updated_at\":\"2023-10-06T23:47:56.678Z\"}" +
            "]}";
    private static final String UPDATE_RESPONSE_JSON = "{\"id\":\"" + TEMPLATE_ID + "\",\"object\":\"template\"}";
    private static final String DELETE_RESPONSE_JSON = "{\"object\":\"template\",\"id\":\"" + TEMPLATE_ID + "\",\"deleted\":true}";
    private static final String DUPLICATE_RESPONSE_JSON = "{\"object\":\"template\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\"}";
    private static final String PUBLISH_RESPONSE_JSON = "{\"id\":\"" + TEMPLATE_ID + "\",\"object\":\"template\"}";

    @Mock
    private IHttpClient httpClient;

    private Templates templates;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        templates = new Templates("test-api-key", httpClient);
    }

    @Test
    public void testCreateTemplate_Success() throws ResendException {
        CreateTemplateOptions createOptions = TemplatesUtil.createTemplateOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateTemplateResponseSuccess response = templates.create(createOptions);

        assertNotNull(response);
        assertEquals("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", response.getId());
        assertEquals("template", response.getObject());
    }

    @Test
    public void testCreateTemplate_ApiError_ThrowsResendException() throws ResendException {
        CreateTemplateOptions createOptions = TemplatesUtil.createTemplateOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422, "{\"name\":\"validation_error\",\"message\":\"Invalid\"}", false);

        when(httpClient.perform(eq("/templates"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> templates.create(createOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetTemplate_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates/" + TEMPLATE_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetTemplateResponseSuccess response = templates.get(TEMPLATE_ID);

        assertNotNull(response);
        assertEquals(TEMPLATE_ID, response.getId());
        assertEquals("reset-password", response.getName());
        assertEquals("published", response.getStatus());
    }

    @Test
    public void testGetTemplateByAlias_Success() throws ResendException {
        String alias = "reset-password";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates/" + alias), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetTemplateResponseSuccess response = templates.get(alias);

        assertNotNull(response);
        assertEquals("reset-password", response.getAlias());
    }

    @Test
    public void testListTemplates_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListTemplatesResponseSuccess response = templates.list();

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals(false, response.getHasMore());
    }

    @Test
    public void testListTemplatesWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(2).after(TEMPLATE_ID).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/templates?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListTemplatesResponseSuccess response = templates.list(params);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }

    @Test
    public void testUpdateTemplate_Success() throws ResendException {
        UpdateTemplateOptions updateOptions = TemplatesUtil.updateTemplateOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates/" + TEMPLATE_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateTemplateResponseSuccess response = templates.update(TEMPLATE_ID, updateOptions);

        assertNotNull(response);
        assertEquals(TEMPLATE_ID, response.getId());
        assertEquals("template", response.getObject());
    }

    @Test
    public void testDeleteTemplate_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, DELETE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates/" + TEMPLATE_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        DeleteTemplateResponseSuccess response = templates.remove(TEMPLATE_ID);

        assertNotNull(response);
        assertEquals(TEMPLATE_ID, response.getId());
        assertEquals(true, response.getDeleted());
    }

    @Test
    public void testDuplicateTemplate_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, DUPLICATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates/" + TEMPLATE_ID + "/duplicate"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        DuplicateTemplateResponseSuccess response = templates.duplicate(TEMPLATE_ID);

        assertNotNull(response);
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getId());
        assertEquals("template", response.getObject());
    }

    @Test
    public void testPublishTemplate_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, PUBLISH_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/templates/" + TEMPLATE_ID + "/publish"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        PublishTemplateResponseSuccess response = templates.publish(TEMPLATE_ID);

        assertNotNull(response);
        assertEquals(TEMPLATE_ID, response.getId());
        assertEquals("template", response.getObject());
    }
}
