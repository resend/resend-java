package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.services.contacts.model.CreateContactRequestOptions;
import com.resend.services.contacts.model.CreateContactResponseSuccess;
import com.resend.services.contacts.model.ListContactsResponseSuccess;
import com.resend.services.contacts.model.RemoveContactResponseSuccess;
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
        CreateContactRequestOptions param = ContactsUtil.createContactRequest();

        when(contacts.create(param))
                .thenReturn(expectedContact);

        CreateContactResponseSuccess createdContact = contacts.create(param);


        assertEquals(createdContact, expectedContact);
        verify(contacts, times(1)).create(param);
    }

    @Test
    public void testRemoveContact_Success() throws ResendException {
        String id = "123";
        String audienceId = "123";
        RemoveContactResponseSuccess removed = ContactsUtil.removeContactResponseSuccess();
        when(contacts.remove(id, audienceId))
                .thenReturn(removed);

        RemoveContactResponseSuccess res = contacts.remove(id, audienceId);

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
}
