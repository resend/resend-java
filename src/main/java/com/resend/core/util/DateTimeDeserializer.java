package com.resend.core.util;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.time.format.DateTimeParseException;

public class DateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

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