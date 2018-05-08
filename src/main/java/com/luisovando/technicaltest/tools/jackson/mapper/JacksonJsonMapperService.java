package com.luisovando.technicaltest.tools.jackson.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;


final class JacksonJsonMapperService implements JsonMapperService {
    private final ObjectMapper mapper;

    JacksonJsonMapperService(final ObjectMapper mapper) {
        super();
        this.mapper = mapper;
    }

    @Override
    public String toJson(final Object instance) throws IOException {
        return mapper.writeValueAsString(instance);
    }

    @Override
    public <T> T fromJson(final String json, final Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    @Override
    public <E> String toJsonCollection(List<E> list) throws JsonProcessingException {
        return mapper.writeValueAsString(list);
    }
}