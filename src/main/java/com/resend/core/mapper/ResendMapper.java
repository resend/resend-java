package com.resend.core.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Converts the provided object into its JSON representation.
     *
     * @param object The object to be converted to JSON.
     * @return The JSON representation of the object.
     */
    @Override
    public String writeValue(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts the provided JSON value into an instance of the specified class.
     *
     * @param value The JSON value to be converted.
     * @param clazz The class to convert the JSON value to.
     * @param <T>   The type of the resulting object.
     * @return An instance of the specified class with values from the JSON value.
     */
    @Override
    public <T> T readValue(String value, Class<T> clazz)  {
        try {
            return mapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
