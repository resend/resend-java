package com.resend.core.service;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.IHttpClient;
import com.resend.services.emails.Emails;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import com.resend.services.emails.model.Template;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

public class BaseServiceTest {

    private final BaseService service = new BaseService("re_test") { };

    @Test
    public void testHandle_SuccessfulResponse_ReturnsParsedBody() throws ResendException {
        CreateEmailResponse response = service.handle(
                new AbstractHttpResponse<>(200, "{\"id\":\"123\"}", true), CreateEmailResponse.class);

        assertEquals("123", response.getId());
    }

    @Test
    public void testHandle_UnparseableBody_ThrowsResendException() {
        String body = "<html>502 Bad Gateway</html>";

        ResendException exception = assertThrows(ResendException.class,
                () -> service.handle(new AbstractHttpResponse<>(200, body, true), CreateEmailResponse.class));

        assertEquals(200, exception.getStatusCode());
        assertEquals(body, exception.getResponseBody());
        assertEquals("Failed to parse the response body as CreateEmailResponse", exception.getMessage());
        assertInstanceOf(UncheckedIOException.class, exception.getCause());
    }

    @Test
    public void testHandle_EmptyBody_ThrowsResendException() {
        assertThrows(ResendException.class,
                () -> service.handle(new AbstractHttpResponse<>(200, "", true), CreateEmailResponse.class));
    }

    @Test
    public void testHandle_JsonNullBody_ThrowsResendException() {
        ResendException exception = assertThrows(ResendException.class,
                () -> service.handle(new AbstractHttpResponse<>(200, "null", true), CreateEmailResponse.class));

        assertNull(exception.getCause());
    }

    @Test
    public void testHandle_ErrorResponse_ThrowsResendExceptionWithApiMessage() {
        ResendException exception = assertThrows(ResendException.class, () -> service.handle(
                new AbstractHttpResponse<>(404, "{\"name\":\"not_found\",\"message\":\"Email not found\"}", false),
                CreateEmailResponse.class));

        assertEquals(404, exception.getStatusCode());
        assertEquals("Email not found", exception.getMessage());
    }

    @Test
    public void testSerialize_UnserializableBody_ThrowsResendException() {
        ResendException exception = assertThrows(ResendException.class, () -> service.serialize(new Object()));

        assertTrue(exception.getMessage().startsWith("Failed to serialize the request body"));
        assertInstanceOf(UncheckedIOException.class, exception.getCause());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testServiceMethod_UnserializableBody_ThrowsResendExceptionWithoutSendingRequest() {
        IHttpClient<String> httpClient = mock(IHttpClient.class);
        Emails emails = new Emails("re_test", httpClient);
        CreateEmailOptions options = CreateEmailOptions.builder()
                .from("a@example.com").to("b@example.com").subject("hi")
                .template(Template.builder().id("template-id").addVariable("unserializable", new Object()).build())
                .build();

        ResendException exception = assertThrows(ResendException.class, () -> emails.send(options));

        assertInstanceOf(UncheckedIOException.class, exception.getCause());
        verifyNoInteractions(httpClient);
    }
}
