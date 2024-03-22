package com.project.productservice.services;

import com.project.productservice.dtos.FakeStoreProductDto;
import com.project.productservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreCategoryService implements CategoryService{
    private RestTemplate restTemplate;
    @Autowired
    public FakeStoreCategoryService(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }
    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList= new ArrayList<>();
        String [] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", String[].class
        );
        for(String string:response)
        {
            categoryList.add(new Category(string));
        }
        return categoryList;
    }
}
