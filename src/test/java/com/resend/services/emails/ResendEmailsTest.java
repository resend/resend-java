package com.resend.services.emails;
import com.resend.services.util.ResendEmailsUtil;
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

public class ResendEmailsTest {
    @Mock
    private ResendEmails resendEmails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resendEmails = mock(ResendEmails.class);
    }

    @Test
    public void testRetrieveEmail_Success() {
        Email expectedEmail = ResendEmailsUtil.createTestEmail();

        when(resendEmails.get(expectedEmail.getId()))
                .thenReturn(expectedEmail);

        Email retrievedEmail = resendEmails.get(expectedEmail.getId());


        assertEquals(expectedEmail, retrievedEmail);
        verify(resendEmails, times(1)).get(expectedEmail.getId());
    }

    @Test
    public void testSendEmail_Success() {
        SendEmailRequest sendEmailRequest = ResendEmailsUtil.createSendEmailRequest();
        SendEmailResponse expectedRes = ResendEmailsUtil.createSendEmailResponse();

        when(resendEmails.send(sendEmailRequest)).thenReturn(expectedRes);

        SendEmailResponse sendEmailResponse = resendEmails.send(sendEmailRequest);

        assertNotNull(sendEmailResponse);
    }
}