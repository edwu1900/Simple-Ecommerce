package org.example.Repository;

import org.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    public Map<String, Order> orders;

    public OrderRepository() {
        orders = new HashMap<>();
    }

    public void createOrder(Order order) {
        String orderId = order.getId();
        orders.put(orderId, order);
    }

    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }
}
