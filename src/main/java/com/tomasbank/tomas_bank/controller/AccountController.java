package com.tomasbank.tomas_bank.controller;

import com.tomasbank.tomas_bank.entity.Account;
import com.tomasbank.tomas_bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;

    @GetMapping("/getByAccountId/{accountId}")
    public ResponseEntity<Account> getByAccountId(@PathVariable Integer accountId) {
        Account account = service.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<List<Account>> getByCustomerId(@PathVariable String customerId) {
        List<Account> account = service.getAccountsByCustomer(customerId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/saveAccount")
    public ResponseEntity<String> saveAccount(@Validated @RequestBody Account account) {
        service.saveAccount(account);
        return new ResponseEntity<>("Account saved successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(@RequestParam Integer accountId) {
        Account account = service.getAccountById(accountId);
        if (account == null) {
            return new ResponseEntity<>("Account with ID " + accountId + " not found.", HttpStatus.NOT_FOUND);
        }
        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            return new ResponseEntity<>("Account with ID " + accountId + " has a balance of " + account.getBalance() +
                    ". Please withdraw the balance before you delete the account!", HttpStatus.BAD_REQUEST);
        }
        if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            return new ResponseEntity<>("Account with ID " + accountId + " has a balance of " + account.getBalance() +
                    ". Please pay off the balance before you delete the account!", HttpStatus.BAD_REQUEST);
        } else {

            boolean deleted = service.deleteAccountById(accountId);

            if (deleted) {
                return new ResponseEntity<>("Account with ID " + accountId + " deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to delete account with ID " + accountId + ".", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
