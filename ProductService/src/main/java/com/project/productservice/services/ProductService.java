package com.project.productservice.services;

import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product getSingleProduct(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    void deleteProduct(Long id);

    List<Product> getProductsOfCategory(String category) throws CategoryNotFoundException;
}
