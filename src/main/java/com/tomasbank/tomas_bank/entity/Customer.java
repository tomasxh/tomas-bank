package com.tomasbank.tomas_bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity(name = "BankCustomer")
@Data
@ToString
public class Customer {
    @Id
    @Column(name = "customer_id", length = 50)
    private String customerId;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String email;

}
