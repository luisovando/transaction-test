package com.luisovando.technicaltest.tools.jackson.mapper;

import java.io.IOException;
import java.util.List;

public interface JsonMapperService {

    String toJson(Object instance) throws IOException;

    <T> T fromJson(String json, Class<T> clazz) throws IOException;

    <E> String toJsonCollection(List<E> list) throws IOException;
}

