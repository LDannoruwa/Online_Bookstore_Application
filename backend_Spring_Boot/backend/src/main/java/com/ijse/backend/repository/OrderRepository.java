package com.ijse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.backend.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    //custom quaries can be added here          
}
