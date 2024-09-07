package com.mycompany.interviewtask.service;

import com.mycompany.interviewtask.model.dto.CustomerDto;
import com.mycompany.interviewtask.model.mapper.CustomerMapper;
import com.mycompany.interviewtask.parser.JsonParser;
import com.mycompany.interviewtask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final JsonParser jsonParser;

    @Value("${customer.json.path}")
    private String customerJsonPath;

    public List<CustomerDto> parserCustomers() {
        return jsonParser.parserJson(customerJsonPath, CustomerDto.class);
    }



  /*public CustomerDto save(CustomerDto customerDto) {

    }*/
}
