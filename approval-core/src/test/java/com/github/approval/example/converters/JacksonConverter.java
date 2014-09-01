package com.github.approval.example.converters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.approval.converters.AbstractStringConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JacksonConverter<T> extends AbstractStringConverter<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Nonnull
    @Override
    protected String getStringForm(@Nullable T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AssertionError("Couldn't convert " + value + " to json!", e);
        }
    }
}
