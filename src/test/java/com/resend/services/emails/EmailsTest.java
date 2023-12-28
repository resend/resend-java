package com.resend.services.emails;
import com.resend.core.exception.ResendException;
import com.resend.services.batch.Batch;
import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.util.EmailsUtil;
import com.resend.services.emails.model.Email;
import com.resend.services.emails.model.CreateEmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmailsTest {
    @Mock
    private Emails emails;

    @Mock
    private Batch batch;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emails = mock(Emails.class);
    }

    @Test
    public void testRetrieveEmail_Success() throws ResendException {
        Email expectedEmail = EmailsUtil.createTestEmail();

        when(emails.get(expectedEmail.getId()))
                .thenReturn(expectedEmail);

        Email retrievedEmail = emails.get(expectedEmail.getId());


        assertEquals(expectedEmail, retrievedEmail);
        verify(emails, times(1)).get(expectedEmail.getId());
    }

    @Test
    public void testSendEmail_Success() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        CreateEmailResponse expectedRes = EmailsUtil.createSendEmailResponse();

        when(emails.send(createEmailOptions)).thenReturn(expectedRes);

        CreateEmailResponse createEmailResponse = emails.send(createEmailOptions);

        assertNotNull(createEmailResponse);
    }

    @Test
    public void testCreateBatchEmails_Success() throws ResendException {
        List<CreateEmailOptions> batchEmailsRequest = EmailsUtil.createBatchEmailOptions();
        CreateBatchEmailsResponse expectedRes = EmailsUtil.createBatchEmailsResponse();

        when(batch.send(batchEmailsRequest)).thenReturn(expectedRes);

        CreateBatchEmailsResponse sendBatchEmailsResponse = batch.send(batchEmailsRequest);

        assertNotNull(sendBatchEmailsResponse);
        assertEquals(expectedRes.getData().size(), sendBatchEmailsResponse.getData().size());
    }
}
