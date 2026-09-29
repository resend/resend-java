package com.resend.core.mapper;

import com.resend.services.emails.model.CreateEmailResponse;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;

public class ResendMapperTest {

    private final ResendMapper mapper = new ResendMapper();

    @Test
    public void testReadValue_ParsesJson() {
        CreateEmailResponse response = mapper.readValue("{\"id\":\"123\",\"unknown\":true}", CreateEmailResponse.class);

        assertEquals("123", response.getId());
    }

    @Test
    public void testReadValue_InvalidJson_Throws() {
        UncheckedIOException exception = assertThrows(UncheckedIOException.class,
                () -> mapper.readValue("<html>Bad Gateway</html>", CreateEmailResponse.class));

        assertTrue(exception.getMessage().contains(CreateEmailResponse.class.getName()));
        assertNotNull(exception.getCause());
    }

    @Test
    public void testWriteValue_UnserializableObject_Throws() {
        assertThrows(UncheckedIOException.class, () -> mapper.writeValue(new Object()));
    }
}
