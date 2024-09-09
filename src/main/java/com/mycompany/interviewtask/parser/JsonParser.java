package com.mycompany.interviewtask.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JsonParser {
    public <T> List<T> parserJsonList(String filePath, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File(filePath);
        if (!file.exists()) {
            log.error("File does not exist: {}", filePath);
            throw new RuntimeException("File does not exist");
        }
        try {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}