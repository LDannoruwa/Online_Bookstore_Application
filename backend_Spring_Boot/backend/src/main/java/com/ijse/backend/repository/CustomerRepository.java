package com.ijse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.backend.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    //custom quaries can be added here    
}
