package com.mycompany.interviewtask.service;

import com.mycompany.interviewtask.model.dto.CustomerDto;
import com.mycompany.interviewtask.model.entity.Customer;
import com.mycompany.interviewtask.model.mapper.CustomerMapper;
import com.mycompany.interviewtask.parser.JsonParser;
import com.mycompany.interviewtask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final JsonParser jsonParser;

    @Value("${customer.json.path}")
    private String customerJsonPath;
    private final static String russianNumber = "+7";

    private List<CustomerDto> parserCustomers() {
        return jsonParser.parserJsonList(customerJsonPath, CustomerDto.class);
    }

    @Transactional
    public List<CustomerDto> saveCustomers() {
        List<CustomerDto> customerDtos = parserCustomers();
        List<String> phoneNumbers = getNonRussianPhones(customerDtos);
        List<String> sortedPhoneNumbers = sortPhoneNumberList(phoneNumbers);

        calculateCustomerRatings(customerDtos);
        savePhoneNumbersToFile(sortedPhoneNumbers);

        List<Customer> customers = customerRepository.saveAll(customerMapper.toEntityList(customerDtos));
        return customerMapper.toDtoList(customers);
    }

    private List<String> getNonRussianPhones(List<CustomerDto> customerDtos) {
        return customerDtos.stream().map(CustomerDto::getPhoneNumber)
                .filter(s -> !s.startsWith(russianNumber)).collect(Collectors.toList());
    }

    private List<String> sortPhoneNumberList(List<String> phoneNumbers) {
        return phoneNumbers.stream().sorted().collect(Collectors.toList());
    }

    private void calculateCustomerRatings(List<CustomerDto> customers) {
        customers.stream().map(customerDto -> {
            int rating = customerDto.getNumberOfPurchases() - customerDto.getNumberOfReturns();
            rating += customerDto.getStatus().getBonus(); // Используем Enum для расчета бонуса
            customerDto.setRating(rating);
            return customerDto;
        }).collect(Collectors.toList());
    }

    private void savePhoneNumbersToFile(List<String> numbers) {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("phone_numbers.txt")))) {
            numbers.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при записи номеров телефонов в файл: " + e.getMessage());
        }
    }
}