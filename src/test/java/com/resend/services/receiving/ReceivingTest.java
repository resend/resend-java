package com.resend.services.receiving;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.receiving.model.*;
import com.resend.services.util.ReceivingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for Receiving service.
 */
public class ReceivingTest {

    @Mock
    private Receiving receiving;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        receiving = mock(Receiving.class);
    }

    @Test
    public void testGetReceivedEmail_Success() throws ResendException {
        ReceivedEmail expectedEmail = ReceivingUtil.createReceivedEmail();

        when(receiving.get(expectedEmail.getId())).thenReturn(expectedEmail);

        ReceivedEmail result = receiving.get(expectedEmail.getId());

        assertNotNull(result);
        assertEquals(expectedEmail.getId(), result.getId());
        assertEquals(expectedEmail.getSubject(), result.getSubject());
        assertEquals(expectedEmail.getFrom(), result.getFrom());
        assertEquals(expectedEmail.getMessageId(), result.getMessageId());
        verify(receiving, times(1)).get(expectedEmail.getId());
    }

    @Test
    public void testListReceivedEmails_Success() throws ResendException {
        ListReceivedEmailsResponse expectedResponse = ReceivingUtil.createListReceivedEmailsResponse();

        when(receiving.list()).thenReturn(expectedResponse);

        ListReceivedEmailsResponse result = receiving.list();

        assertNotNull(result);
        assertEquals(expectedResponse.getData().size(), result.getData().size());
        assertEquals(expectedResponse.hasMore(), result.hasMore());
        assertEquals("list", result.getObject());
        verify(receiving, times(1)).list();
    }

    @Test
    public void testListReceivedEmailsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        ListReceivedEmailsResponse expectedResponse = ReceivingUtil.createListReceivedEmailsResponse();

        when(receiving.list(params)).thenReturn(expectedResponse);

        ListReceivedEmailsResponse result = receiving.list(params);

        assertNotNull(result);
        assertEquals(expectedResponse.getData().size(), result.getData().size());
        verify(receiving, times(1)).list(params);
    }

    @Test
    public void testGetAttachment_Success() throws ResendException {
        String emailId = "4ef9a417-02e9-4d39-ad75-9611e0fcc33c";
        String attachmentId = "2a0c9ce0-3112-4728-976e-47ddcd16a318";
        AttachmentDetails expectedDetails = ReceivingUtil.createAttachmentDetails();

        when(receiving.getAttachment(emailId, attachmentId)).thenReturn(expectedDetails);

        AttachmentDetails result = receiving.getAttachment(emailId, attachmentId);

        assertNotNull(result);
        assertEquals(expectedDetails.getId(), result.getId());
        assertEquals(expectedDetails.getFilename(), result.getFilename());
        assertEquals(expectedDetails.getDownloadUrl(), result.getDownloadUrl());
        assertEquals("attachment", result.getObject());
        verify(receiving, times(1)).getAttachment(emailId, attachmentId);
    }

    @Test
    public void testListAttachments_Success() throws ResendException {
        String emailId = "4ef9a417-02e9-4d39-ad75-9611e0fcc33c";
        ListAttachmentsResponse expectedResponse = ReceivingUtil.createListAttachmentsResponse();

        when(receiving.listAttachments(emailId)).thenReturn(expectedResponse);

        ListAttachmentsResponse result = receiving.listAttachments(emailId);

        assertNotNull(result);
        assertEquals(expectedResponse.getData().size(), result.getData().size());
        assertEquals(expectedResponse.hasMore(), result.hasMore());
        assertEquals("list", result.getObject());
        verify(receiving, times(1)).listAttachments(emailId);
    }

    @Test
    public void testListAttachmentsWithPagination_Success() throws ResendException {
        String emailId = "4ef9a417-02e9-4d39-ad75-9611e0fcc33c";
        ListParams params = ListParams.builder().limit(5).build();
        ListAttachmentsResponse expectedResponse = ReceivingUtil.createListAttachmentsResponse();

        when(receiving.listAttachments(emailId, params)).thenReturn(expectedResponse);

        ListAttachmentsResponse result = receiving.listAttachments(emailId, params);

        assertNotNull(result);
        assertEquals(expectedResponse.getData().size(), result.getData().size());
        verify(receiving, times(1)).listAttachments(emailId, params);
    }
}
