package com.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.backend.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order updateOrder(long id, Order order);
    void deleteOrder(long id);
}
