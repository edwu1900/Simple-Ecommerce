package org.example.controller;

import org.example.dto.CreateOrderRequest;
import org.example.dto.CreateOrderResponse;
import org.example.model.Order;
import org.example.model.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    private OrderService orderService;
    private ProductService productService;
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        orderService = mock(OrderService.class);
        productService = mock(ProductService.class);
        orderController = new OrderController(orderService, productService);
    }

    @Test
    public void testCreateOrder_Success() throws Exception {
        CreateOrderRequest request = new CreateOrderRequest();
        request.productId = "product1";
        request.quantity = 2;

        Product product = new Product();
        product.setId("product1");
        product.setPrice(100.0);

        when(productService.getProductById("product1")).thenReturn(product);
        when(productService.reduceStock("product1", 2)).thenReturn(true);

        Order mockOrder = new Order();
        mockOrder.setId("SPL1234567890");
        mockOrder.setTotal(200.0);
        when(orderService.createOrder(anyString(), eq(200.0))).thenReturn(mockOrder);

        CreateOrderResponse response = orderController.createOrder(request);

        assertNotNull(response);
        assertEquals(200.0, response.total);
    }

    @Test
    public void testCreateOrder_ProductNotFound() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.productId = "p404";
        request.quantity = 1;

        when(productService.getProductById("p404")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                orderController.createOrder(request));

        assertEquals("product not found", exception.getMessage());
    }

    @Test
    public void testGetOrder_Success() {
        Order mockOrder = new Order();
        mockOrder.setId("123");
        mockOrder.setTotal(300.0);

        when(orderService.getOrderById("123")).thenReturn(mockOrder);

        Order result = orderController.getOrder("123");

        assertNotNull(result);
        assertEquals("123", result.getId());
    }

    @Test
    public void testGetOrder_NotFound() {
        when(orderService.getOrderById("123")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                orderController.getOrder("123"));

        assertEquals("Order not found", exception.getMessage());
    }
}
