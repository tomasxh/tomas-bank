package com.tomasbank.tomas_bank.repository;

import com.tomasbank.tomas_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM [dbo].[BankTransaction] " +
            "WHERE [transaction_id] = :transactionId")
    public Transaction getTransaction(Integer transactionId);
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM [dbo].[BankTransaction] AS t " +
            "JOIN [dbo].[BankAccount] AS a ON a.account_id = t.account_id " +
            "WHERE t.account_id = :accountId AND t.transaction_date = :transactionDate")
    public List<Transaction> getTransactionByAccountIdAndDate(Integer accountId, LocalDateTime transactionDate);

}
