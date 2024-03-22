package com.project.productservice.controllers;

import com.project.productservice.common.AuthenticationCommon;
import com.project.productservice.dtos.ExceptionDto;
import com.project.productservice.dtos.Role;
import com.project.productservice.dtos.UserDto;
import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import com.project.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private RestTemplate restTemplate;
    private ProductService productService;
    private AuthenticationCommon authenticationCommon;
    @Autowired
    public ProductController(RestTemplate restTemplate,
                             @Qualifier("fakeStoreProductService") ProductService productService,
                             AuthenticationCommon authenticationCommon) {
        this.restTemplate = restTemplate;
        this.productService = productService;
        this.authenticationCommon=authenticationCommon;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id")Long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(/*@RequestHeader("AuthenticationToken") String token*/) {
//        UserDto userDto=authenticationCommon.validateToken(token);
//        if(userDto==null)
//        {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        boolean isAdmin=false;
//        for(Role role:userDto.getRoles())
//        {
//            if(role.getName().equals("ADMIN"))
//            {
//               isAdmin = true;
//            }
//        }
//        if(!isAdmin)
//        {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
        List<Product> productList=productService.getAllProducts();
//        Using for making wrong assert in test case
//        for (Product product:productList)
//        {
//            product.setTitle("Hello "+ product.getTitle());
//        }
        return new ResponseEntity<>(productList,HttpStatus.ACCEPTED);
    }
    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product)
    {
        return new ResponseEntity<>(productService.addNewProduct(product),HttpStatus.FOUND);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id,product);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> getAllCategories(@PathVariable("category") String category) throws CategoryNotFoundException {
        return productService.getProductsOfCategory(category);
    }

    /* Class Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(Exception exception)
    {
        ExceptionDto exceptionDto= new ExceptionDto();
        exceptionDto.setMessage("From Class");
        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }*/
}
