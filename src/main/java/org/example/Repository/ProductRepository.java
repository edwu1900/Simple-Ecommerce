package org.example.Repository;

import org.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    public Map<String, Product> products = new HashMap<>();

    public ProductRepository() {
        products.put("1", new Product("1", "product1", 1.0, 100));
        products.put("2", new Product("2", "product2", 10.0, 200));
        products.put("3", new Product("3", "product3", 50.0, 50));
        products.put("4", new Product("4", "product4", 100.0, 10));
    }

    public Product getProductById(String id) {
        return products.get(id);
    }
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    public void updateProduct(String id, Product product) {
        products.put(id, product);
    }
}
