package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setup() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void testGetProducts_ReturnsProductList() {
        Product p1 = new Product();
        p1.setId("p1");
        p1.setName("Product 1");
        p1.setPrice(100.0);

        Product p2 = new Product();
        p2.setId("p2");
        p2.setName("Product 2");
        p2.setPrice(200.0);

        List<Product> mockProducts = Arrays.asList(p1, p2);

        when(productService.getAllProducts()).thenReturn(mockProducts);

        List<Product> result = productController.getProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("p1", result.getFirst().getId());
        verify(productService, times(1)).getAllProducts();
    }

}