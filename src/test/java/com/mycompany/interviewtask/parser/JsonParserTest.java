package com.mycompany.interviewtask.parser;

import com.mycompany.interviewtask.model.dto.CustomerDto;
import com.mycompany.interviewtask.model.enums.CustomerStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JsonParserTest {
    @Spy
    private JsonParser jsonParser;

    private String customerJsonPath;
    private List<CustomerDto> customerDtos;

    @BeforeEach
    public void setUp() {
        customerDtos = List.of(CustomerDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .status(CustomerStatus.SILVER)
                .numberOfPurchases(100)
                .numberOfReturns(9)
                .phoneNumber("+7-999-888-77-66")
                .build());
    }

    @Test
    public void correctParseToCustomerDto() {
        customerJsonPath = "src/test/resources/customersTest.json";
        List<CustomerDto> list = jsonParser.parserJsonList(customerJsonPath, CustomerDto.class);

        assertEquals("Ivan", list.get(0).getFirstName());
        assertEquals(CustomerStatus.SILVER.getBonus(), list.get(0).getStatus().getBonus());
    }

    @Test
    public void correctExceptionInParser() {
        customerJsonPath = "src/test/resources/customeTest.json";
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> jsonParser.parserJsonList(customerJsonPath, CustomerDto.class));

        assertEquals("File does not exist", runtimeException.getMessage());
    }
}