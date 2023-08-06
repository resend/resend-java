package com.resend.core.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * An interface for mapping between JSON representation and Java objects using ObjectMapper.
 */
public interface IMapper {

    /**
     * Converts the provided object into its JSON representation.
     *
     * @param object The object to be converted to JSON.
     * @return The JSON representation of the object.
     * @throws JsonProcessingException If an error occurs during JSON serialization.
     */
    String writeValue(Object object) throws JsonProcessingException;

    /**
     * Converts the provided JSON value into an instance of the specified class.
     *
     * @param value The JSON value to be converted.
     * @param clazz The class to convert the JSON value to.
     * @param <T>   The type of the resulting object.
     * @return An instance of the specified class with values from the JSON value.
     * @throws JsonProcessingException If an error occurs during JSON deserialization.
     */
    <T> T readValue(String value, Class<T> clazz) throws JsonProcessingException;

}

