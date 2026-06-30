package com.resend.services.contactproperties;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.contactproperties.model.*;
import com.resend.services.util.ContactPropertiesUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ContactPropertiesTest {

    private static final String CREATE_RESPONSE_JSON = "{\"id\":\"123\",\"object\":\"contact_property\"}";
    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"123\",\"key\":\"age\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"type\":\"number\",\"fallback_value\":\"25\"}";
    private static final String UPDATE_RESPONSE_JSON = "{\"id\":\"123\",\"object\":\"contact_property\"}";
    private static final String REMOVE_RESPONSE_JSON = "{\"id\":\"123\",\"object\":\"contact_property\",\"deleted\":true}";
    private static final String LIST_RESPONSE_JSON =
            "{\"data\":[" +
            "{\"id\":\"1\",\"key\":\"age\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"type\":\"string\",\"fallback_value\":\"25\"}," +
            "{\"id\":\"2\",\"key\":\"city\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"type\":\"string\",\"fallback_value\":\"New York\"}," +
            "{\"id\":\"3\",\"key\":\"subscribed\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"type\":\"string\",\"fallback_value\":\"fallback\"}" +
            "],\"object\":\"list\",\"has_more\":false}";

    @Mock
    private IHttpClient httpClient;

    private ContactProperties contactProperties;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactProperties = new ContactProperties("test-api-key", httpClient);
    }

    @Test
    public void testCreateContactProperty_Success() throws ResendException {
        CreateContactPropertyOptions param = ContactPropertiesUtil.createContactPropertyRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/contact-properties"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateContactPropertyResponseSuccess created = contactProperties.create(param);

        assertNotNull(created);
        assertEquals("123", created.getId());
        assertEquals("contact_property", created.getObject());
    }

    @Test
    public void testCreateContactProperty_ApiError_ThrowsResendException() throws ResendException {
        CreateContactPropertyOptions param = ContactPropertiesUtil.createContactPropertyRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422, "{\"name\":\"validation_error\",\"message\":\"Invalid\"}", false);

        when(httpClient.perform(eq("/contact-properties"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> contactProperties.create(param));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testListContactProperties_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/contact-properties"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactPropertiesResponseSuccess res = contactProperties.list();

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testGetContactProperty_Success() throws ResendException {
        String id = "123";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/contact-properties/" + id), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ContactProperty res = contactProperties.get(id);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("age", res.getKey());
        assertEquals("number", res.getType());
    }

    @Test
    public void testUpdateContactProperty_Success() throws ResendException {
        UpdateContactPropertyOptions params = ContactPropertiesUtil.createUpdateOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/contact-properties/123"), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateContactPropertyResponseSuccess res = contactProperties.update(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("contact_property", res.getObject());
    }

    @Test
    public void testRemoveContactProperty_Success() throws ResendException {
        String id = "123";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/contact-properties/" + id), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveContactPropertyResponseSuccess res = contactProperties.remove(id);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertTrue(res.isDeleted());
    }
}
