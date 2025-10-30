package com.resend.services.contactproperties;

import com.resend.core.exception.ResendException;
import com.resend.services.contactproperties.model.*;
import com.resend.services.util.ContactPropertiesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ContactPropertiesTest {

    @Mock
    private ContactProperties contactProperties;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactProperties = mock(ContactProperties.class);
    }

    @Test
    public void testCreateContactProperty_Success() throws ResendException {
        CreateContactPropertyResponseSuccess expected = ContactPropertiesUtil.createContactPropertyResponseSuccess();
        CreateContactPropertyOptions param = ContactPropertiesUtil.createContactPropertyRequest();

        when(contactProperties.create(param))
                .thenReturn(expected);

        CreateContactPropertyResponseSuccess created = contactProperties.create(param);

        assertEquals(created, expected);
        verify(contactProperties, times(1)).create(param);
    }

    @Test
    public void testListContactProperties_Success() throws ResendException {
        ListContactPropertiesResponseSuccess expectedResponse = ContactPropertiesUtil.createContactPropertiesListResponse();

        when(contactProperties.list())
                .thenReturn(expectedResponse);

        ListContactPropertiesResponseSuccess res = contactProperties.list();

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testGetContactProperty_Success() throws ResendException {
        String id = "123";
        ContactProperty expected = ContactPropertiesUtil.getContactProperty();

        when(contactProperties.get(id)).thenReturn(expected);

        ContactProperty res = contactProperties.get(id);

        assertNotNull(res);
        assertEquals(expected, res);
        verify(contactProperties, times(1)).get(id);
    }

    @Test
    public void testUpdateContactProperty_Success() throws ResendException {
        UpdateContactPropertyOptions params = ContactPropertiesUtil.createUpdateOptions();
        UpdateContactPropertyResponseSuccess expectedResponse = ContactPropertiesUtil.updateContactPropertyResponseSuccess();

        when(contactProperties.update(params))
                .thenReturn(expectedResponse);

        UpdateContactPropertyResponseSuccess res = contactProperties.update(params);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testRemoveContactProperty_Success() throws ResendException {
        String id = "123";
        RemoveContactPropertyResponseSuccess removed = ContactPropertiesUtil.removeContactPropertyResponseSuccess();

        when(contactProperties.remove(id))
                .thenReturn(removed);

        RemoveContactPropertyResponseSuccess res = contactProperties.remove(id);

        assertEquals(removed, res);
        verify(contactProperties, times(1)).remove(id);
    }
}
