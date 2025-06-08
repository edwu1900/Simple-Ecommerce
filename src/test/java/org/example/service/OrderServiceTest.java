package org.example.service;

import org.example.Repository.OrderRepository;
import org.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void testCreateOrder_create() {

        String orderId = "Test123";
        double total = 99.99;

        Order result = orderService.createOrder(orderId, total);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
        assertEquals(total, result.getTotal());

        verify(orderRepository, times(1)).createOrder(result);
    }

    @Test
    void testGetOrderById() {

        String orderId = "Test";
        Order mockOrder = new Order(orderId, 100.0);

        when(orderRepository.getOrderById(orderId)).thenReturn(mockOrder);

        Order result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
        assertEquals(100.0, result.getTotal());

        verify(orderRepository, times(1)).getOrderById(orderId);
    }

    @Test
    void testGetOrderById_OrderNotFound() {
        String orderId = "Test";

        when(orderRepository.getOrderById(orderId)).thenReturn(null);

        Order result = orderService.getOrderById(orderId);

        assertNull(result);
        verify(orderRepository, times(1)).getOrderById(orderId);
    }
}