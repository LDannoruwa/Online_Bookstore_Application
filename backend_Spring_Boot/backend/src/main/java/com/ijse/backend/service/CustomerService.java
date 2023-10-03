package com.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.backend.entity.Customer;

@Service
public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(long id, Customer customer);
    void deleteCustomer(long id);
}
