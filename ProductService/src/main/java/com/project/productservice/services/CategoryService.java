package com.project.productservice.services;

import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    List<Category> getAllCategories();
}
