package com.resend.core.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ByteArraySerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize(byte[] bytes, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (byte b : bytes) {
            jgen.writeNumber(unsignedToBytes(b));
        }
        jgen.writeEndArray();
    }

    private static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }
}