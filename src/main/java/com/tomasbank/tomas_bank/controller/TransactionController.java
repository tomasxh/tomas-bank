package com.tomasbank.tomas_bank.controller;

import com.tomasbank.tomas_bank.entity.Transaction;
import com.tomasbank.tomas_bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService service;
    @GetMapping("/getById/{transactionId}")
    public ResponseEntity<Transaction> getById(@PathVariable Integer transactionId){
        Transaction transaction = service.getTransactionById(transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    @GetMapping("/getByAccountIdAndDate/{accountId}/{transactionDate}")
    public ResponseEntity<List<Transaction>> getByAccountIdAndDate(@PathVariable Integer accountId, LocalDateTime transactionDate){
        List<Transaction> transactions = service.getTransactionsByAccountAndDate(accountId, transactionDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    @PostMapping("/saveTransaction")
    public ResponseEntity<String> saveTransaction(@Validated @RequestBody Transaction transaction) {
        service.saveTransaction(transaction);
        return new ResponseEntity<>("Transaction saved successfully.", HttpStatus.CREATED);
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(Integer fromAccountId, Integer toAccountId, BigDecimal amount){
        service.transferFunds(fromAccountId, toAccountId, amount);
        return new ResponseEntity<>("Funds transferred successfully.", HttpStatus.OK);
    }
}
