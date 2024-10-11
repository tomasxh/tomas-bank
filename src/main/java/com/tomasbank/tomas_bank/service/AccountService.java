package com.tomasbank.tomas_bank.service;

import com.tomasbank.tomas_bank.entity.Account;
import com.tomasbank.tomas_bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;
    public Account getAccountById(Integer accountId){
        return repository.getAccount(accountId);
    }
    public List<Account> getAccountsByCustomer(String customerId){
        return repository.getAccountByCustomerId(customerId);
    }
    @Transactional
    public void saveAccount(Account account){
        repository.save(account);
    }
    @Transactional
    public boolean deleteAccountById(Integer accountId){
        if (repository.existsById(accountId)){
            repository.deleteById(accountId);
            return true;
        }
        return false;
    }

}
