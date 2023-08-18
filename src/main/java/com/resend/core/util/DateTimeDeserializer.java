package com.resend.core.util;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.time.format.DateTimeParseException;

/**
 * A custom JSON deserializer for OffsetDateTime objects.
 */
public class DateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    /**
     * Deserializes a JSON string representation of OffsetDateTime into an OffsetDateTime object.
     *
     * @param parser   The JSON parser.
     * @param context  The deserialization context.
     * @return The deserialized OffsetDateTime object.
     * @throws IOException If there's an issue reading the JSON input.
     */
    @Override
    public OffsetDateTime deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        String date = parser.getText();
        try {
            return OffsetDateTime.parse(date);
        } catch (DateTimeParseException e) {
            // Handle the parsing exception, for example, log an error
            throw new IOException("Error parsing OffsetDateTime: " + date, e);
        }
    }
}