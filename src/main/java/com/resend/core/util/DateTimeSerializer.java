package com.resend.core.util;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;

/**
 * A custom JSON serializer for OffsetDateTime objects.
 */
public class DateTimeSerializer extends JsonSerializer<OffsetDateTime> {

    /**
     * Serializes an OffsetDateTime object into a JSON string representation.
     *
     * @param value    The OffsetDateTime value to serialize.
     * @param gen      The JSON generator.
     * @param provider The serializer provider.
     * @throws IOException If there's an issue writing the JSON output.
     */
    @Override
    public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString());
    }
}

