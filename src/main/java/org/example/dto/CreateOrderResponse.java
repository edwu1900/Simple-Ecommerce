package org.example.dto;

import org.example.model.Order;

public class CreateOrderResponse {
    public String orderId;
    public double total;

    public CreateOrderResponse(CreateOrderRequest createOrderRequest) {}
    public CreateOrderResponse(Order order) {
        this.orderId = order.getId();
        this.total = order.getTotal();
    }
}
