package org.example.service;

import org.example.Repository.OrderRepository;
import org.example.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String orderId, double total) {
        Order order = new Order(orderId, total);
        orderRepository.createOrder(order);
        return order;
    }

    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }
}
