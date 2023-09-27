package com.resend.services.emails;
import com.resend.core.exception.ResendException;
import com.resend.services.util.EmailsUtil;
import com.resend.services.emails.model.Email;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmailsTest {
    @Mock
    private Emails emails;

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
        SendEmailRequest sendEmailRequest = EmailsUtil.createSendEmailRequest();
        SendEmailResponse expectedRes = EmailsUtil.createSendEmailResponse();

        when(emails.send(sendEmailRequest)).thenReturn(expectedRes);

        SendEmailResponse sendEmailResponse = emails.send(sendEmailRequest);

        assertNotNull(sendEmailResponse);
    }
}
