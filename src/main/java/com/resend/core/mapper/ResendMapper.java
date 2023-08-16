package com.resend.core.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    }

    /**
     * Converts the provided object into its JSON representation.
     *
     * @param object The object to be converted to JSON.
     * @return The JSON representation of the object.
     * @throws JsonProcessingException If an error occurs during JSON serialization.
     */
    @Override
    public String writeValue(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * Converts the provided JSON value into an instance of the specified class.
     *
     * @param value The JSON value to be converted.
     * @param clazz The class to convert the JSON value to.
     * @param <T>   The type of the resulting object.
     * @return An instance of the specified class with values from the JSON value.
     * @throws JsonProcessingException If an error occurs during JSON deserialization.
     */
    @Override
    public <T> T readValue(String value, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(value, clazz);
    }
}
