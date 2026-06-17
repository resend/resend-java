package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.services.contacts.model.CreateContactImportOptions;
import com.resend.services.contacts.model.CreateContactImportResponseSuccess;
import com.resend.services.contacts.model.GetContactImportResponseSuccess;
import com.resend.services.contacts.model.ListContactImportsParams;
import com.resend.services.contacts.model.ListContactImportsResponseSuccess;
import com.resend.services.util.ContactImportsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContactImportsTest {

    @Mock
    private ContactImports contactImports;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactImports = mock(ContactImports.class);
    }

    @Test
    public void testCreateContactImport_Success() throws ResendException {
        File file = new File("contacts.csv");
        CreateContactImportOptions options = ContactImportsUtil.createContactImportOptions(file);
        CreateContactImportResponseSuccess expected = ContactImportsUtil.createContactImportResponseSuccess();

        when(contactImports.create(options)).thenReturn(expected);

        CreateContactImportResponseSuccess res = contactImports.create(options);

        assertNotNull(res);
        assertEquals(expected.getId(), res.getId());
        assertEquals(expected.getObject(), res.getObject());
        verify(contactImports, times(1)).create(options);
    }

    @Test
    public void testGetContactImport_Success() throws ResendException {
        String id = ContactImportsUtil.CONTACT_IMPORT_ID;
        GetContactImportResponseSuccess expected = ContactImportsUtil.getContactImportResponseSuccess();

        when(contactImports.get(id)).thenReturn(expected);

        GetContactImportResponseSuccess res = contactImports.get(id);

        assertNotNull(res);
        assertEquals(expected.getId(), res.getId());
        assertEquals(expected.getStatus(), res.getStatus());
        assertEquals(expected.getCounts().getTotal(), res.getCounts().getTotal());
        verify(contactImports, times(1)).get(id);
    }

    @Test
    public void testListContactImports_Success() throws ResendException {
        ListContactImportsResponseSuccess expected = ContactImportsUtil.listContactImportsResponseSuccess();

        when(contactImports.list()).thenReturn(expected);

        ListContactImportsResponseSuccess res = contactImports.list();

        assertNotNull(res);
        assertEquals(expected.getData().size(), res.getData().size());
        assertEquals(expected.getObject(), res.getObject());
        verify(contactImports, times(1)).list();
    }

    @Test
    public void testListContactImportsWithParams_Success() throws ResendException {
        ListContactImportsParams params = ListContactImportsParams.builder()
                .limit(10)
                .status("completed")
                .build();
        ListContactImportsResponseSuccess expected = ContactImportsUtil.listContactImportsResponseSuccess();

        when(contactImports.list(params)).thenReturn(expected);

        ListContactImportsResponseSuccess res = contactImports.list(params);

        assertNotNull(res);
        assertEquals(expected.getData().size(), res.getData().size());
        assertEquals("completed", params.getStatus());
        assertEquals(Integer.valueOf(10), params.getLimit());
        verify(contactImports, times(1)).list(params);
    }
}
