package com.project.productservice.services;

import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import com.project.productservice.repositories.CategoryRepository;
import com.project.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository)
    {
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product with this id not found");
        }
        Product product=productOptional.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(Product product) {
        if(product.getCategory()!=null)
        {
            Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
            if(categoryOptional.isEmpty())
            {
                product.setCategory(categoryRepository.save(product.getCategory()));
            }
            else {
                product.setCategory(categoryOptional.get());
            }
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> currentlySavedProductOptional = productRepository.findById(id);
        if(currentlySavedProductOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product with this id does not exists");
        }
        Product currentlySavedProduct=currentlySavedProductOptional.get();
        if(product.getCategory()!=null)
        {
            Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
            if(categoryOptional.isEmpty())
            {
                Category category= categoryRepository.save(product.getCategory());
                currentlySavedProduct.setCategory(category);
            }
            else {
                currentlySavedProduct.setCategory(categoryOptional.get());
            }
        }
        currentlySavedProduct.setDescription(product.getDescription());
        currentlySavedProduct.setImageUrl(product.getImageUrl());
        currentlySavedProduct.setTitle(product.getTitle());
        currentlySavedProduct.setPrice(product.getPrice());
        return productRepository.save(currentlySavedProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> currentlySavedProductOptional = productRepository.findById(id);
        if(currentlySavedProductOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product with this id does not exists");
        }
        Product currentlySavedProduct=currentlySavedProductOptional.get();
        if(product.getCategory()!=null)
        {
            Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
            if(categoryOptional.isEmpty())
            {
                Category category= categoryRepository.save(product.getCategory());
                currentlySavedProduct.setCategory(category);
            }
            else {
                currentlySavedProduct.setCategory(categoryOptional.get());
            }
        }
        currentlySavedProduct.setDeleted(product.isDeleted());
        if(product.getDescription()!=null)
        {
            currentlySavedProduct.setDescription(product.getDescription());
        }
        if(product.getImageUrl()!=null)
        {
            currentlySavedProduct.setImageUrl(product.getImageUrl());
        }
        if(product.getTitle()!=null)
        {
            currentlySavedProduct.setTitle(product.getTitle());
        }
        if(product.getPrice()!=0)
        {
            currentlySavedProduct.setPrice(product.getPrice());
        }
        return productRepository.save(currentlySavedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsOfCategory(String category) throws CategoryNotFoundException {
        Optional<Category> categoryOptional= categoryRepository.findByName(category);
        if(categoryOptional.isEmpty())
        {
            throw new CategoryNotFoundException("Category Not Found");
        }
        return productRepository.findAllByCategory(categoryOptional.get());
    }
}
