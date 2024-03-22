package com.project.productservice.controllers;

import com.project.productservice.models.Category;
import com.project.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }
    @GetMapping("")
    public List<Category> getALlCategories()
    {
        return categoryService.getAllCategories();
    }
}
