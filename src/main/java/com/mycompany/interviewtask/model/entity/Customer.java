package com.mycompany.interviewtask.model.entity;

import com.mycompany.interviewtask.model.enums.CustomerStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "status")
    String status;

    @Column(name = "name_of_purchases")
    Integer numberOfPurchases;

    @Column(name = "number_of_returns")
    Integer numberOfReturns;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    CustomerStatus rating;
}