package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.contacts.model.CreateContactImportOptions;
import com.resend.services.contacts.model.CreateContactImportResponseSuccess;
import com.resend.services.contacts.model.GetContactImportResponseSuccess;
import com.resend.services.contacts.model.ListContactImportsParams;
import com.resend.services.contacts.model.ListContactImportsResponseSuccess;
import com.resend.services.util.ContactImportsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ContactImportsTest {

    private static final String IMPORT_ID = ContactImportsUtil.CONTACT_IMPORT_ID;

    private static final String CREATE_IMPORT_JSON =
            "{\"object\":\"contact_import\",\"id\":\"" + IMPORT_ID + "\"}";

    private static final String GET_IMPORT_JSON =
            "{\"object\":\"contact_import\",\"id\":\"" + IMPORT_ID + "\"," +
            "\"status\":\"completed\"," +
            "\"created_at\":\"2026-05-15 18:32:37.823+00\"," +
            "\"completed_at\":\"2026-05-15 18:33:42.916+00\"," +
            "\"counts\":{\"total\":1200,\"created\":800,\"updated\":300,\"skipped\":75,\"failed\":25}}";

    private static final String LIST_IMPORTS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"object\":\"contact_import\",\"id\":\"" + IMPORT_ID + "\"," +
            "\"status\":\"completed\"," +
            "\"created_at\":\"2026-05-15 18:32:37.823+00\"," +
            "\"completed_at\":\"2026-05-15 18:33:42.916+00\"," +
            "\"counts\":{\"total\":1200,\"created\":800,\"updated\":300,\"skipped\":75,\"failed\":25}}" +
            "]}";

    @Mock
    private IHttpClient httpClient;

    private ContactImports contactImports;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactImports = new ContactImports("test-api-key", httpClient);
    }

    @Test
    public void testCreateContactImport_Success() throws ResendException {
        File file = new File("contacts.csv");
        CreateContactImportOptions options = ContactImportsUtil.createContactImportOptions(file);
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_IMPORT_JSON, true);

        when(httpClient.performMultipart(eq("/contacts/imports"), anyString(), eq(HttpMethod.POST),
                any(File.class), any(MediaType.class), anyMap(), isNull()))
                .thenReturn(httpResponse);

        CreateContactImportResponseSuccess res = contactImports.create(options);

        assertNotNull(res);
        assertEquals(IMPORT_ID, res.getId());
        assertEquals("contact_import", res.getObject());
    }

    @Test
    public void testCreateContactImport_ApiError_ThrowsResendException() throws ResendException {
        File file = new File("contacts.csv");
        CreateContactImportOptions options = ContactImportsUtil.createContactImportOptions(file);
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid file\"}", false);

        when(httpClient.performMultipart(eq("/contacts/imports"), anyString(), eq(HttpMethod.POST),
                any(File.class), any(MediaType.class), anyMap(), isNull()))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> contactImports.create(options));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetContactImport_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_IMPORT_JSON, true);

        when(httpClient.perform(eq("/contacts/imports/" + IMPORT_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetContactImportResponseSuccess res = contactImports.get(IMPORT_ID);

        assertNotNull(res);
        assertEquals(IMPORT_ID, res.getId());
        assertEquals("completed", res.getStatus());
        assertEquals(Integer.valueOf(1200), res.getCounts().getTotal());
    }

    @Test
    public void testListContactImports_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_IMPORTS_JSON, true);

        when(httpClient.perform(eq("/contacts/imports"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactImportsResponseSuccess res = contactImports.list();

        assertNotNull(res);
        assertEquals(1, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListContactImportsWithParams_Success() throws ResendException {
        ListContactImportsParams params = ListContactImportsParams.builder()
                .limit(10)
                .status("completed")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_IMPORTS_JSON, true);

        when(httpClient.perform(anyString(), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListContactImportsResponseSuccess res = contactImports.list(params);

        assertNotNull(res);
        assertEquals(1, res.getData().size());
        assertEquals("completed", res.getData().get(0).getStatus());
    }
}
