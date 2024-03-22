package com.project.productservice.controllers;

import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Product;
import com.project.productservice.repositories.ProductRepository;
import com.project.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductService productService;
    //@Test
//    void testProductsSameAsService()
//    {
//        //arrange
//        List<Product> productList=new ArrayList<>();
//        List<Product> productListCopy=new ArrayList<>();
//        productList.add(new Product());
//        productList.add(new Product());
//        productList.add(new Product());
//        for(Product product:productList)
//        {
//            product.setTitle("Random title" + new Random());
//        }
//        for (int i=0;i<productList.size();i++)
//        {
//            productListCopy.add(new Product());
//            productListCopy.get(i).setTitle(productList.get(i).getTitle());
//        }
//        when(
//                productService.getAllProducts()
//        ).thenReturn(
//          productList
//        );
//        //act
//        ResponseEntity<List<Product>> listResponseEntity=productController.getAllProducts();
//
//        assertEquals(productList.size(),listResponseEntity.getBody().size());
//        for(int i=0;i<listResponseEntity.getBody().size();i++)
//        {
//            assertEquals(productListCopy.get(i).getTitle(),listResponseEntity.getBody().get(i).getTitle());
//        }
//    }
//
//    @Test
//    void testNonExistingProductThrowsException()
//    {
//        //arrange
//        when(productRepository.findById(10L))
//                .thenReturn(Optional.empty());
//
//        //act
//
//        assertThrows(ProductNotFoundException.class,()-> productController.getProduct(10L));
//    }
}