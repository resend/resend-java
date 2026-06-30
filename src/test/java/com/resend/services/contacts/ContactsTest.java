package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.contacts.model.*;
import com.resend.services.util.ContactsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ContactsTest {

    private static final String CREATE_CONTACT_JSON = "{\"id\":\"123\",\"object\":\"contact\"}";
    private static final String REMOVE_CONTACT_JSON = "{\"id\":\"123\",\"object\":\"contact\",\"deleted\":true}";
    private static final String UPDATE_CONTACT_JSON = "{\"id\":\"123\",\"object\":\"contact\"}";
    private static final String GET_CONTACT_JSON =
            "{\"object\":\"contacts\",\"id\":\"123\",\"email\":\"user@example.com\"," +
            "\"first_name\":\"test\",\"last_name\":\"test\"," +
            "\"created_at\":\"2025-04-30T12:00:00Z\",\"unsubscribed\":false}";
    private static final String LIST_CONTACTS_JSON =
            "{\"data\":[" +
            "{\"id\":\"1\",\"email\":\"frodo.baggins@shire.com\",\"first_name\":\"Frodo\"," +
            "\"last_name\":\"Baggins\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"unsubscribed\":false}," +
            "{\"id\":\"2\",\"email\":\"aragorn.strider@gondor.com\",\"first_name\":\"Aragorn\"," +
            "\"last_name\":\"Strider\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"unsubscribed\":false}," +
            "{\"id\":\"3\",\"email\":\"legolas.greenleaf@woodland.com\",\"first_name\":\"Legolas\"," +
            "\"last_name\":\"Greenleaf\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"unsubscribed\":false}" +
            "],\"object\":\"list\"}";
    private static final String UPDATE_TOPICS_JSON =
            "{\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\"}";

    @Mock
    private IHttpClient httpClient;

    private Contacts contacts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contacts = new Contacts("test-api-key", httpClient);
    }

    @Test
    public void testCreateContact_Success() throws ResendException {
        CreateContactOptions param = ContactsUtil.createContactRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateContactResponseSuccess response = contacts.create(param);

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("contact", response.getObject());
    }

    @Test
    public void testCreateContact_ApiError_ThrowsResendException() throws ResendException {
        CreateContactOptions param = ContactsUtil.createContactRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid email\"}", false);

        when(httpClient.perform(eq("/contacts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> contacts.create(param));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testRemoveContact_Success() throws ResendException {
        RemoveContactOptions params = RemoveContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/e169aa45-1ecf-4183-9955-b1499d5701d3"), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveContactResponseSuccess res = contacts.remove(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
    }

    @Test
    public void testListContactsBySegmentId_Success() throws ResendException {
        ListContactsOptions options = ListContactsOptions.builder()
                .segmentId("segment-123")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(eq("/segments/segment-123/contacts"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list(options);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactsByAudienceId_Success() throws ResendException {
        ListContactsOptions options = ListContactsOptions.builder()
                .audienceId("audience-123")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(eq("/segments/audience-123/contacts"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list(options);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactsSegmentIdTakesPrecedenceOverAudienceId_Success() {
        ListContactsOptions options = ListContactsOptions.builder()
                .segmentId("segment-123")
                .audienceId("audience-456")
                .build();

        assertEquals("segment-123", options.resolvedSegmentId());
    }

    @Test
    public void testListContactsWithPaginationBySegmentId_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        ListContactsOptions options = ListContactsOptions.builder()
                .segmentId("segment-123")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list(options, params);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactsGlobalWhenNoId_Success() throws ResendException {
        ListContactsOptions options = ListContactsOptions.builder().build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(eq("/contacts"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list(options);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
    }

    @Test
    public void testListContacts_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(eq("/segments/123/contacts"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list("123");

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list("123", params);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testUpdateContact_Success() throws ResendException {
        UpdateContactOptions params = ContactsUtil.createUpdateOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/123"), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateContactResponseSuccess res = contacts.update(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("contact", res.getObject());
    }

    @Test
    public void testGetContactById_Success() throws ResendException {
        GetContactOptions params = GetContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/e169aa45-1ecf-4183-9955-b1499d5701d3"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetContactResponseSuccess res = contacts.get(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("user@example.com", res.getEmail());
    }

    @Test
    public void testGetContactByEmail_Success() throws ResendException {
        GetContactOptions params = GetContactOptions.builder()
                .email("user@example.com")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/user@example.com"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetContactResponseSuccess res = contacts.get(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("user@example.com", res.getEmail());
    }

    @Test
    public void testCreateGlobalContact_Success() throws ResendException {
        CreateContactOptions param = CreateContactOptions.builder()
                .email("user@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateContactResponseSuccess response = contacts.create(param);

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("contact", response.getObject());
    }

    @Test
    public void testListGlobalContacts_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(eq("/contacts"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list();

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListGlobalContactsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_CONTACTS_JSON, true);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactsResponseSuccess res = contacts.list(params);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testGetGlobalContactById_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/" + contactId), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetContactResponseSuccess res = contacts.get(contactId);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("user@example.com", res.getEmail());
    }

    @Test
    public void testGetGlobalContactByEmail_Success() throws ResendException {
        String contactEmail = "user@example.com";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/" + contactEmail), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetContactResponseSuccess res = contacts.get(contactEmail);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("user@example.com", res.getEmail());
    }

    @Test
    public void testRemoveGlobalContactById_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/" + contactId), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveContactResponseSuccess res = contacts.remove(contactId);

        assertNotNull(res);
        assertEquals("123", res.getId());
    }

    @Test
    public void testUpdateGlobalContact_Success() throws ResendException {
        UpdateContactOptions params = UpdateContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .firstName("Jane")
                .lastName("Smith")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/e169aa45-1ecf-4183-9955-b1499d5701d3"), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateContactResponseSuccess res = contacts.update(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("contact", res.getObject());
    }

    @Test
    public void testGetContactWithoutSegmentId_Success() throws ResendException {
        GetContactOptions params = GetContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_CONTACT_JSON, true);

        when(httpClient.perform(eq("/contacts/e169aa45-1ecf-4183-9955-b1499d5701d3"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetContactResponseSuccess res = contacts.get(params);

        assertNotNull(res);
        assertEquals("123", res.getId());
    }
}
