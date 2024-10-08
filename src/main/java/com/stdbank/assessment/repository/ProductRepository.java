package com.stdbank.assessment.repository;

import com.stdbank.assessment.model.Product;

import java.util.List;

public interface ProductRepository {
     void saveProduct(Product product);
     Product updateProduct(Product product);
     Product findProductById(String id);
     List<Product> findAllProducts();
     void deleteProduct(String id);
}
