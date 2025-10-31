package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.contacts.model.ListContactTopicsResponse;
import com.resend.services.contacts.model.UpdateContactTopicsOptions;
import com.resend.services.contacts.model.UpdateContactTopicsResponse;
import com.resend.services.util.ContactsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ContactTopicsTest {

    @Mock
    private ContactTopics contactTopics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactTopics = mock(ContactTopics.class);
    }

    @Test
    public void testListTopicsById_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        ListContactTopicsResponse expectedResponse = ContactsUtil.createContactTopicsListResponse();

        when(contactTopics.list(contactId))
                .thenReturn(expectedResponse);

        ListContactTopicsResponse res = contactTopics.list(contactId);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
        verify(contactTopics, times(1)).list(contactId);
    }

    @Test
    public void testListTopicsByEmail_Success() throws ResendException {
        String contactEmail = "steve.wozniak@gmail.com";
        ListContactTopicsResponse expectedResponse = ContactsUtil.createContactTopicsListResponse();

        when(contactTopics.list(contactEmail))
                .thenReturn(expectedResponse);

        ListContactTopicsResponse res = contactTopics.list(contactEmail);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
        verify(contactTopics, times(1)).list(contactEmail);
    }

    @Test
    public void testListTopicsWithPagination_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        ListParams params = ListParams.builder()
                .limit(10)
                .build();
        ListContactTopicsResponse expectedResponse = ContactsUtil.createContactTopicsListResponse();

        when(contactTopics.list(contactId, params))
                .thenReturn(expectedResponse);

        ListContactTopicsResponse res = contactTopics.list(contactId, params);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
        verify(contactTopics, times(1)).list(contactId, params);
    }

    @Test
    public void testUpdateTopics_Success() throws ResendException {
        UpdateContactTopicsOptions options = ContactsUtil.createUpdateTopicsOptions();
        UpdateContactTopicsResponse expectedResponse = ContactsUtil.updateContactTopicsResponse();

        when(contactTopics.update(options))
                .thenReturn(expectedResponse);

        UpdateContactTopicsResponse res = contactTopics.update(options);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        verify(contactTopics, times(1)).update(options);
    }
}
