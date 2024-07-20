package org.example.productcatalogservice_may2024.controllers;

import org.example.productcatalogservice_may2024.dtos.CategoryDto;
import org.example.productcatalogservice_may2024.dtos.ProductDto;
import org.example.productcatalogservice_may2024.models.Category;
import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    //@Qualifier("productservicestub")
    IProductService iProductService;


    @GetMapping("/{pid}/{uid}")
    public ProductDto getProductBasedOnUserScope(@PathVariable Long pid,@PathVariable Long uid) {
        Product product = iProductService.getProductBasedOnUserScope(pid,uid);

        if(product == null) {
            return null;
        }

        return getProductDto(product);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> results = new ArrayList<>();
        List<Product> products = iProductService.getAllProducts();
        for(Product product : products) {
            results.add(getProductDto(product));
        }

        return results;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long productId) {
        try {
            if (productId <= 0) {
                throw new IllegalArgumentException("invalid productId");
            }

            Product product = iProductService.getProductById(productId);
            ProductDto body = getProductDto(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "anurag");
            //return new ResponseEntity<>(body, headers, HttpStatus.OK);
            return body;
        } catch(IllegalArgumentException ex) {
            throw ex;
            //return handleExceptions(ex);
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
       Product product = getProduct(productDto);
       Product result = iProductService.createProduct(product);
       return getProductDto(result);
    }


    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        Product newProduct = iProductService.replaceProduct(id,product);
        return getProductDto(newProduct);
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
