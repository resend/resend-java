package com.resend.services.emails;
import com.resend.ResendUtil;
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

public class EmailServiceTest {
    @Mock
    private ResendEmails resendEmails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resendEmails = mock(ResendEmails.class);
    }

    @Test
    public void testRetrieveEmail_Success() {
        Email expectedEmail = ResendUtil.createTestEmail();

        when(resendEmails.retrieveEmail(expectedEmail.getId()))
                .thenReturn(expectedEmail);

        Email retrievedEmail = resendEmails.retrieveEmail(expectedEmail.getId());


        assertEquals(expectedEmail, retrievedEmail);
        verify(resendEmails, times(1)).retrieveEmail(expectedEmail.getId());
    }

    @Test
    public void testSendEmail_Success() {
        SendEmailRequest sendEmailRequest = ResendUtil.createSendEmailRequest();
        SendEmailResponse expectedRes = ResendUtil.createSendEmailResponse();

        when(resendEmails.sendEmail(sendEmailRequest)).thenReturn(expectedRes);

        SendEmailResponse sendEmailResponse = resendEmails.sendEmail(sendEmailRequest);

        assertNotNull(sendEmailResponse);
    }
}
