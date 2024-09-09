package com.mycompany.interviewtask.service;

import com.mycompany.interviewtask.model.dto.CustomerDto;
import com.mycompany.interviewtask.model.entity.Customer;
import com.mycompany.interviewtask.model.enums.CustomerStatus;
import com.mycompany.interviewtask.model.mapper.CustomerMapper;
import com.mycompany.interviewtask.parser.JsonParser;
import com.mycompany.interviewtask.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerServiceTest {
    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private JsonParser jsonParser;

    @InjectMocks
    private CustomerService customerService;

    @Value("${customer.json.path}")
    private String customerJsonPath;

    private List<CustomerDto> customerDtos;

    @BeforeEach
    public void setUp() {
        customerDtos = List.of(
                CustomerDto.builder()
                        .firstName("Ivan")
                        .lastName("Ivanov")
                        .phoneNumber("+7-999-888-77-66")
                        .status(CustomerStatus.SILVER)
                        .numberOfPurchases(100)
                        .numberOfReturns(9)
                        .build(),
                CustomerDto.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .status(CustomerStatus.SILVER)
                        .phoneNumber("+1-123-456-78-90")
                        .numberOfPurchases(50)
                        .numberOfReturns(5)
                        .build()
        );
    }

    @Test
    public void testSaveCustomers() {
        when(jsonParser.parserJsonList(customerJsonPath, CustomerDto.class)).thenReturn(customerDtos);
        List<CustomerDto> customerDtoList = customerService.saveCustomers();

        verify(customerRepository, times(1)).saveAll(any());
    }

    @Test
    public void testSaveCustomers_NonRussianPhonesFiltered() {
        when(jsonParser.parserJsonList(customerJsonPath, CustomerDto.class)).thenReturn(customerDtos);
        when(customerMapper.toEntityList(any())).thenReturn(List.of(new Customer(), new Customer()));
        when(customerRepository.saveAll(any())).thenReturn(List.of(new Customer(), new Customer()));
        when(customerMapper.toDtoList(any())).thenReturn(customerDtos);

        List<CustomerDto> result = customerService.saveCustomers();

        assertEquals(2, result.size());
        assertEquals("+1-123-456-78-90", result.get(1).getPhoneNumber());
    }
}
