package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.contacts.model.*;
import com.resend.services.util.ContactsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ContactsTest {

    @Mock
    private Contacts contacts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contacts = mock(Contacts.class);
    }

    @Test
    public void testCreateContact_Success() throws ResendException {
        CreateContactResponseSuccess expectedContact = ContactsUtil.createContactResponseSuccess();
        CreateContactOptions param = ContactsUtil.createContactRequest();

        when(contacts.create(param))
                .thenReturn(expectedContact);

        CreateContactResponseSuccess createdContact = contacts.create(param);


        assertEquals(createdContact, expectedContact);
        verify(contacts, times(1)).create(param);
    }

    @Test
    public void testRemoveContact_Success() throws ResendException {
        RemoveContactOptions params = RemoveContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();

        RemoveContactResponseSuccess removed = ContactsUtil.removeContactResponseSuccess();
        when(contacts.remove(params))
                .thenReturn(removed);

        RemoveContactResponseSuccess res = contacts.remove(params);

        assertEquals(removed, res);
    }

    @Test
    public void testListContacts_Success() throws ResendException {
        String audienceId = "123";
        ListContactsResponseSuccess expectedResponse = ContactsUtil.createContactsListResponse();

        when(contacts.list(audienceId))
                .thenReturn(expectedResponse);

        ListContactsResponseSuccess res = contacts.list(audienceId);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testListContactsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(3).build();

        String audienceId = "123";
        ListContactsResponseSuccess expectedResponse = ContactsUtil.createContactsListResponse();

        when(contacts.list(audienceId, params))
                .thenReturn(expectedResponse);

        ListContactsResponseSuccess res = contacts.list(audienceId, params);

        assertNotNull(res);
        assertEquals(params.getLimit(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testUpdateContact_Success() throws ResendException {
        UpdateContactOptions params = ContactsUtil.createUpdateOptions();
        UpdateContactResponseSuccess expectedResponse = ContactsUtil.updateContactResponseSuccess();

        when(contacts.update(params))
                .thenReturn(expectedResponse);

        UpdateContactResponseSuccess res = contacts.update(params);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testGetContactById_Success() throws ResendException {
        GetContactOptions params = GetContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();
        GetContactResponseSuccess expected = ContactsUtil.getContactResponseSuccess();

        when(contacts.get(params)).thenReturn(expected);

        GetContactResponseSuccess res = contacts.get(params);

        assertNotNull(res);
        assertEquals(expected, res);
        verify(contacts, times(1)).get(params);
    }

    @Test
    public void testGetContactByEmail_Success() throws ResendException {
        GetContactOptions params = GetContactOptions.builder()
                .email("user@example.com")
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();
        GetContactResponseSuccess expected = ContactsUtil.getContactResponseSuccess();

        when(contacts.get(params)).thenReturn(expected);

        GetContactResponseSuccess res = contacts.get(params);

        assertNotNull(res);
        assertEquals(expected, res);
        verify(contacts, times(1)).get(params);
    }

    // Global contacts tests (without segment ID)

    @Test
    public void testCreateGlobalContact_Success() throws ResendException {
        CreateContactResponseSuccess expectedContact = ContactsUtil.createContactResponseSuccess();
        CreateContactOptions param = CreateContactOptions.builder()
                .email("user@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        when(contacts.create(param))
                .thenReturn(expectedContact);

        CreateContactResponseSuccess createdContact = contacts.create(param);

        assertEquals(createdContact, expectedContact);
        verify(contacts, times(1)).create(param);
    }

    @Test
    public void testListGlobalContacts_Success() throws ResendException {
        ListContactsResponseSuccess expectedResponse = ContactsUtil.createContactsListResponse();

        when(contacts.list())
                .thenReturn(expectedResponse);

        ListContactsResponseSuccess res = contacts.list();

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testListGlobalContactsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(3).build();

        ListContactsResponseSuccess expectedResponse = ContactsUtil.createContactsListResponse();

        when(contacts.list(params))
                .thenReturn(expectedResponse);

        ListContactsResponseSuccess res = contacts.list(params);

        assertNotNull(res);
        assertEquals(params.getLimit(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testGetGlobalContactById_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        GetContactResponseSuccess expected = ContactsUtil.getContactResponseSuccess();

        when(contacts.get(contactId)).thenReturn(expected);

        GetContactResponseSuccess res = contacts.get(contactId);

        assertNotNull(res);
        assertEquals(expected, res);
        verify(contacts, times(1)).get(contactId);
    }

    @Test
    public void testGetGlobalContactByEmail_Success() throws ResendException {
        String contactEmail = "user@example.com";
        GetContactResponseSuccess expected = ContactsUtil.getContactResponseSuccess();

        when(contacts.get(contactEmail)).thenReturn(expected);

        GetContactResponseSuccess res = contacts.get(contactEmail);

        assertNotNull(res);
        assertEquals(expected, res);
        verify(contacts, times(1)).get(contactEmail);
    }

    @Test
    public void testRemoveGlobalContactById_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        RemoveContactResponseSuccess removed = ContactsUtil.removeContactResponseSuccess();

        when(contacts.remove(contactId))
                .thenReturn(removed);

        RemoveContactResponseSuccess res = contacts.remove(contactId);

        assertEquals(removed, res);
        verify(contacts, times(1)).remove(contactId);
    }

    @Test
    public void testUpdateGlobalContact_Success() throws ResendException {
        UpdateContactOptions params = UpdateContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .firstName("Jane")
                .lastName("Smith")
                .build();
        UpdateContactResponseSuccess expectedResponse = ContactsUtil.updateContactResponseSuccess();

        when(contacts.update(params))
                .thenReturn(expectedResponse);

        UpdateContactResponseSuccess res = contacts.update(params);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testGetContactWithoutSegmentId_Success() throws ResendException {
        GetContactOptions params = GetContactOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .build();
        GetContactResponseSuccess expected = ContactsUtil.getContactResponseSuccess();

        when(contacts.get(params)).thenReturn(expected);

        GetContactResponseSuccess res = contacts.get(params);

        assertNotNull(res);
        assertEquals(expected, res);
        verify(contacts, times(1)).get(params);
    }
}
