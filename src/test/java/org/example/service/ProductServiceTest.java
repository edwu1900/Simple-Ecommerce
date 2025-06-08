package org.example.service;

import org.example.Repository.ProductRepository;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void testGetAllProducts_ReturnsList() {
        List<Product> mockList = Arrays.asList(
                new Product("product1", "A", 100.0, 100),
                new Product("product2", "B", 50.0, 50)
        );
        when(productRepository.getAllProducts()).thenReturn(mockList);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById_ReturnsProduct() {
        Product mockProduct = new Product("product1", "A", 999.99, 10);
        when(productRepository.getProductById("product1")).thenReturn(mockProduct);

        Product result = productService.getProductById("product1");

        assertNotNull(result);
        assertEquals("product1", result.getId());
        assertEquals("A", result.getName());
    }

    @Test
    void testReduceStock_Success() {
        Product product = new Product("product1", "A", 500.0, 10);
        when(productRepository.getProductById("product1")).thenReturn(product);

        boolean result = productService.reduceStock("product1", 2);

        assertTrue(result);
        verify(productRepository, times(1)).updateProduct("product1", product);
    }


}