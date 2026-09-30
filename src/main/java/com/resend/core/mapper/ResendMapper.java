package com.resend.core.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jspecify.annotations.Nullable;

import java.io.UncheckedIOException;

/**
 * Implementation of the IMapper interface for mapping between JSON representation and Java objects using ObjectMapper.
 */
public class ResendMapper implements IMapper {

    private final ObjectMapper mapper;

    /**
     * Constructs a new ResendMapper with a pre-configured ObjectMapper instance.
     */
    public ResendMapper() {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Converts the provided object into its JSON representation.
     *
     * @param object The object to be converted to JSON.
     * @return The JSON representation of the object.
     * @throws UncheckedIOException If the object can't be serialized.
     */
    @Override
    public String writeValue(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException("Failed to serialize " + object.getClass().getName() + " to JSON", e);
        }
    }

    /**
     * Converts the provided JSON value into an instance of the specified class.
     *
     * @param value The JSON value to be converted.
     * @param clazz The class to convert the JSON value to.
     * @param <T>   The type of the resulting object.
     * @return An instance of the specified class with values from the JSON value, or {@code null} if the JSON value is
     *         the literal {@code null}.
     * @throws UncheckedIOException If the value isn't valid JSON for the specified class.
     */
    @Override
    public <T> @Nullable T readValue(String value, Class<T> clazz)  {
        try {
            return mapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException("Failed to parse JSON into " + clazz.getName(), e);
        }
    }
}
