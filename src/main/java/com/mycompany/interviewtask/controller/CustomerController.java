package com.mycompany.interviewtask.controller;

import com.mycompany.interviewtask.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> save() {
        try {
            return ResponseEntity.ok(customerService.saveCustomers());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
    }
}
