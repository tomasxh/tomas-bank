package com.tomasbank.tomas_bank.service;

import com.tomasbank.tomas_bank.entity.Account;
import com.tomasbank.tomas_bank.entity.Transaction;
import com.tomasbank.tomas_bank.repository.AccountRepository;
import com.tomasbank.tomas_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository repository;
    @Autowired
    AccountRepository accountRepository;
    public Transaction getTransactionById(Integer transactionId){
        return repository.getTransaction(transactionId);
    }
    public List<Transaction> getTransactionsByAccountAndDate(Integer accountId, LocalDateTime transactionDate){
        return repository.getTransactionByAccountIdAndDate(accountId, transactionDate);
    }
    @Transactional
    public void saveTransaction(Transaction transaction){
        repository.save(transaction);
    }
    @Transactional
    public void transferFunds(Integer fromAccountId, Integer toAccountId, BigDecimal amount){
        Account fromAccount = accountRepository.getAccount(fromAccountId);
        Account toAccount = accountRepository.getAccount(toAccountId);
        if (fromAccount.getBalance().compareTo(amount) < 0){
            throw new IllegalArgumentException("Insufficient funds in source account.");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(fromAccount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        repository.save(transaction);


    }
}
