package com.mycompany.interviewtask.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JsonParser {
    public <T> List<T> parserJson(String filePath, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> objects = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            log.error("File does not exist or is not readable: {}", filePath);
            throw new RuntimeException("File does not exist or is not readable");
        }
        try {
            JavaType type = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);
            objects = objectMapper.readValue(filePath, type);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return objects;
    }
}
