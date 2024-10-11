package com.tomasbank.tomas_bank.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "BankAccount")
@Data
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    @Column(name = "account_number")
    private String accountNumber;
    @Column
    private BigDecimal balance;
    @Column(name = "date_created")
    private LocalDate dateCreated;

}
