package com.mycompany.interviewtask.model.dto;

import com.mycompany.interviewtask.model.enums.CustomerStatus;
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
    CustomerStatus status;
    Integer numberOfPurchases;
    Integer numberOfReturns;
    String phoneNumber;
    Integer rating;
}
