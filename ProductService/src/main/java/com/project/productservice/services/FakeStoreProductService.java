package com.project.productservice.services;

import com.project.productservice.dtos.FakeStoreProductDto;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate,RedisTemplate<String,Object> redisTemplate)
    {
        this.restTemplate=restTemplate;
        this.redisTemplate=redisTemplate;
    }
    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreDto(Product product)
    {
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        return fakeStoreProductDto;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Product p = (Product) redisTemplate.opsForHash().get("PRODUCT","PRODUCT_" + id);
        if(nonNull(p))
        {
            return p;
        }
        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,FakeStoreProductDto.class
        );
        if(fakeStoreProductDto==null)
        {
            throw new ProductNotFoundException("Product with id "+id+" not found!!!");
        }
        p=convertFakeStoreDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCT","PRODUCT_"+id,p);
        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList= new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",FakeStoreProductDto[].class
        );
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos)
        {
            productList.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product addNewProduct(Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(
                convertProductToFakeStoreDto(product), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor=new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products",
                HttpMethod.POST, requestCallback, responseExtractor);
        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    @Override
    public List<Product> getProductsOfCategory(String category) {
        List<Product> productList= new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,FakeStoreProductDto[].class
        );
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos)
        {
            productList.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }
}
