package com.talalanguage.api.infrastructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersistenceJsonCodec {

    private static final TypeReference<List<String>> STRING_LIST = new TypeReference<>() {
    };

    private final ObjectMapper objectMapper;

    public PersistenceJsonCodec(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String writeStringList(List<String> values) {
        try {
            return objectMapper.writeValueAsString(values);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Could not serialize string list.", exception);
        }
    }

    public List<String> readStringList(String value) {
        try {
            return objectMapper.readValue(value, STRING_LIST);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Could not deserialize string list.", exception);
        }
    }
}
