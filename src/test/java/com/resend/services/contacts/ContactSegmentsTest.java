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

public class ContactSegmentsTest {

    @Mock
    private ContactSegments contactSegments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactSegments = mock(ContactSegments.class);
    }

    @Test
    public void testAddContactToSegmentById_Success() throws ResendException {
        AddContactToSegmentOptions options = ContactsUtil.createAddContactToSegmentOptions();
        AddContactToSegmentResponseSuccess expectedResponse = ContactsUtil.addContactToSegmentResponseSuccess();

        when(contactSegments.add(options))
                .thenReturn(expectedResponse);

        AddContactToSegmentResponseSuccess res = contactSegments.add(options);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        verify(contactSegments, times(1)).add(options);
    }

    @Test
    public void testAddContactToSegmentByEmail_Success() throws ResendException {
        AddContactToSegmentOptions options = AddContactToSegmentOptions.builder()
                .email("steve.wozniak@gmail.com")
                .segmentId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();
        AddContactToSegmentResponseSuccess expectedResponse = ContactsUtil.addContactToSegmentResponseSuccess();

        when(contactSegments.add(options))
                .thenReturn(expectedResponse);

        AddContactToSegmentResponseSuccess res = contactSegments.add(options);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        verify(contactSegments, times(1)).add(options);
    }

    @Test
    public void testRemoveContactFromSegmentById_Success() throws ResendException {
        RemoveContactFromSegmentOptions options = ContactsUtil.createRemoveContactFromSegmentOptions();
        RemoveContactFromSegmentResponseSuccess expectedResponse = ContactsUtil.removeContactFromSegmentResponseSuccess();

        when(contactSegments.remove(options))
                .thenReturn(expectedResponse);

        RemoveContactFromSegmentResponseSuccess res = contactSegments.remove(options);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        assertEquals(expectedResponse.getDeleted(), res.getDeleted());
        verify(contactSegments, times(1)).remove(options);
    }

    @Test
    public void testRemoveContactFromSegmentByEmail_Success() throws ResendException {
        RemoveContactFromSegmentOptions options = RemoveContactFromSegmentOptions.builder()
                .email("steve.wozniak@gmail.com")
                .segmentId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();
        RemoveContactFromSegmentResponseSuccess expectedResponse = ContactsUtil.removeContactFromSegmentResponseSuccess();

        when(contactSegments.remove(options))
                .thenReturn(expectedResponse);

        RemoveContactFromSegmentResponseSuccess res = contactSegments.remove(options);

        assertNotNull(res);
        assertEquals(expectedResponse.getId(), res.getId());
        assertEquals(expectedResponse.getDeleted(), res.getDeleted());
        verify(contactSegments, times(1)).remove(options);
    }

    @Test
    public void testListContactSegmentsById_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        ListContactSegmentsResponseSuccess expectedResponse = ContactsUtil.listContactSegmentsResponseSuccess();

        when(contactSegments.list(contactId))
                .thenReturn(expectedResponse);

        ListContactSegmentsResponseSuccess res = contactSegments.list(contactId);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
        verify(contactSegments, times(1)).list(contactId);
    }

    @Test
    public void testListContactSegmentsByEmail_Success() throws ResendException {
        String contactEmail = "steve.wozniak@gmail.com";
        ListContactSegmentsResponseSuccess expectedResponse = ContactsUtil.listContactSegmentsResponseSuccess();

        when(contactSegments.list(contactEmail))
                .thenReturn(expectedResponse);

        ListContactSegmentsResponseSuccess res = contactSegments.list(contactEmail);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
        verify(contactSegments, times(1)).list(contactEmail);
    }

    @Test
    public void testListContactSegmentsWithPagination_Success() throws ResendException {
        String contactId = "e169aa45-1ecf-4183-9955-b1499d5701d3";
        ListParams params = ListParams.builder()
                .limit(10)
                .build();
        ListContactSegmentsResponseSuccess expectedResponse = ContactsUtil.listContactSegmentsResponseSuccess();

        when(contactSegments.list(contactId, params))
                .thenReturn(expectedResponse);

        ListContactSegmentsResponseSuccess res = contactSegments.list(contactId, params);

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
        verify(contactSegments, times(1)).list(contactId, params);
    }
}
