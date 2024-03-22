package com.project.productservice.services;

import com.project.productservice.models.Category;
import com.project.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService{
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
