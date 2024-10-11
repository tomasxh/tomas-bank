package com.tomasbank.tomas_bank.repository;

import com.tomasbank.tomas_bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM [dbo].[BankAccount] " +
            "WHERE [account_id] = :accountId")
    public Account getAccount(Integer accountId);
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM [dbo].[BankAccount] AS a " +
            "JOIN [dbo].[BankCustomer] AS c ON c.customer_id = a.customer_id " +
            "WHERE a.customer_id = :customerId")
    public List<Account> getAccountByCustomerId(String customerId);

}
