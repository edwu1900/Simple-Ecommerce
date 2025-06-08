package org.example.dto;

public class CreateOrderRequest {
    public String productId;
    public int quantity;
    public CreateOrderRequest() {}
    public CreateOrderRequest(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
