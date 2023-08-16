package com.resend.core.service;
import com.resend.ResendUtil;
import com.resend.core.mapper.ResendMapper;
import com.resend.core.model.Email;
import com.resend.core.model.SendEmailRequest;
import com.resend.core.model.SendEmailResponse;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.provider.AuthenticationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmailServiceTest {
    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = mock(EmailService.class);
    }

    @Test
    public void testRetrieveEmail_Success() {
        Email expectedEmail = ResendUtil.createTestEmail();

        when(emailService.retrieveEmail(expectedEmail.getId()))
                .thenReturn(expectedEmail);

        Email retrievedEmail = emailService.retrieveEmail(expectedEmail.getId());


        assertEquals(expectedEmail, retrievedEmail);
        verify(emailService, times(1)).retrieveEmail(expectedEmail.getId());
    }

    @Test
    public void testSendEmail_Success() {
        SendEmailRequest sendEmailRequest = ResendUtil.createSendEmailRequest();
        SendEmailResponse expectedRes = ResendUtil.createSendEmailResponse();

        when(emailService.sendEmail(sendEmailRequest)).thenReturn(expectedRes);

        SendEmailResponse sendEmailResponse = emailService.sendEmail(sendEmailRequest);

        assertNotNull(sendEmailResponse);
    }
}
