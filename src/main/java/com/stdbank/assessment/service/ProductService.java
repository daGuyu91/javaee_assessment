package com.stdbank.assessment.service;

import com.stdbank.assessment.model.Product;
import com.stdbank.assessment.model.User;
import com.stdbank.assessment.repository.ProductRepository;
import com.stdbank.assessment.repository.UserRepository;
import jakarta.inject.Inject;

import java.util.List;

public class ProductService {
    @Inject
    private ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.saveProduct(product);
    }
    public void deleteProduct(Product product){
        productRepository.deleteProduct(String.valueOf(product.getId()));
    }
    public void updateProduct(Product product){
        productRepository.updateProduct(product);
    }
    public Product findProductById(int id){
        return productRepository.findProductById(String.valueOf(id));
    }
    public List<Product> getAllProducts(){
        return productRepository.findAllProducts();
    }

}
