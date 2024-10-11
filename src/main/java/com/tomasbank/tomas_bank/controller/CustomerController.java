package com.tomasbank.tomas_bank.controller;

import com.tomasbank.tomas_bank.entity.Customer;
import com.tomasbank.tomas_bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;
    @GetMapping("/getById/{customerId}")
    public ResponseEntity<Customer> getById(@PathVariable String customerId){
        Customer customer = service.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping("/saveCustomer")
    public ResponseEntity<String> saveCustomer(@Validated @RequestBody Customer customer) {
        service.saveCustomer(customer);
        return new ResponseEntity<>("Customer saved successfully.", HttpStatus.CREATED);
    }
    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
        service.updateCustomer(customer);
        return new ResponseEntity<>("Customer updated successfully.", HttpStatus.OK);
    }
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(String customerId) {
        boolean deleted = service.deleteCustomerById(customerId);

        if (deleted) {
            return new ResponseEntity<>("Customer with ID " + customerId + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer with ID " + customerId + " not found.", HttpStatus.NOT_FOUND);
        }
    }



}
