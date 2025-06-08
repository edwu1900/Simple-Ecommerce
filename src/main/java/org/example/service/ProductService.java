package org.example.service;

import org.example.Repository.ProductRepository;
import org.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }
    public boolean reduceStock(String id, int quantity) {
        Product product = productRepository.getProductById(id);
        if(product != null) {
            if(product.getStock() - quantity < 0) {
                return false;
            }
            product.setStock(product.getStock() - quantity);
            try {
                productRepository.updateProduct(id, product);
            }catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
