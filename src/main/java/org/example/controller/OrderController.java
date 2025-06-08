package org.example.controller;

import org.example.dto.CreateOrderRequest;
import org.example.dto.CreateOrderResponse;
import org.example.model.Order;
import org.example.model.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/API/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }
    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        Product product = productService.getProductById(request.productId);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }
        if(!productService.reduceStock(request.productId, request.quantity)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product has insufficient stock");
        }
        double total = product.getPrice() * request.quantity;
        String orderId = "SPL" + UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 10);
        Order order;
        try{
            order = orderService.createOrder(orderId, total);
        }catch(Exception e) {
            throw new Exception("Error while submitting an order");
        }
        if(order != null) {
            return new CreateOrderResponse(order);
        }
        else {
            throw new Exception("Order was not created");
        }
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id){
        Order order = orderService.getOrderById(id);
        if(order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        return order;
    }
}
