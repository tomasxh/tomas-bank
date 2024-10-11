package com.tomasbank.tomas_bank.service;

import com.tomasbank.tomas_bank.entity.Customer;
import com.tomasbank.tomas_bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;
    public Customer getCustomerById(String customerId){
        return repository.getCustomer(customerId);
    }
    @Transactional
    public void saveCustomer(Customer customer){
        repository.save(customer);
    }
    @Transactional
    public void updateCustomer(Customer customer){
        repository.updateCustomerById(customer.getName(), customer.getSurname(), customer.getAddress(),
                customer.getPhoneNumber(), customer.getEmail(), customer.getCustomerId());
    }
    @Transactional
    public boolean deleteCustomerById(String customerId){
        if (repository.existsById(customerId)){
            repository.deleteById(customerId);
            return true;
        }
        return false;
    }

}
