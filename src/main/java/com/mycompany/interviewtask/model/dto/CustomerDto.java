package com.mycompany.interviewtask.model.dto;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerDto {
    String firstName;
    String lastName;
    String status;
    Integer numberOfPurchases;
    Integer numberOfReturns;
    String phoneNumber;
    Integer rating;
}
