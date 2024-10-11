package com.tomasbank.tomas_bank.repository;

import com.tomasbank.tomas_bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM [dbo].[BankCustomer] " +
            "WHERE [customer_id] = :customerId")
    public Customer getCustomer(@Param("customerId") String customerId);
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE [dbo].[BankCustomer] " +
            "SET name = :name, " +
            "surname = :surname, " +
            "address = :address, " +
            "phone_number = :phoneNumber, " +
            "email = :email " +
            "WHERE customer_id = :customerId ")
    public void updateCustomerById(String name, String surname, String address,
                                   String phoneNumber, String email, String customerId);

}
