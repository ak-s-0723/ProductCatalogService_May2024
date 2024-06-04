package org.example.productcatalogservice_may2024.controllers;

import org.example.productcatalogservice_may2024.dtos.CategoryDto;
import org.example.productcatalogservice_may2024.dtos.ProductDto;
import org.example.productcatalogservice_may2024.models.Category;
import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.services.FakeStoreProductService;
import org.example.productcatalogservice_may2024.services.IFakeStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    IFakeStoreProductService iFakeStoreProductService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        try {
            if (productId <= 0) {
                throw new IllegalArgumentException("invalid productId");
            }
            Product product = iFakeStoreProductService.getProductById(productId);
            ProductDto body = getProductDto(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "anurag");
            return new ResponseEntity<>(body, headers, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ProductDto createProduct(ProductDto productDto) {
    return null;
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        Product product1 = iFakeStoreProductService.replaceProduct(id, product);
        return getProductDto(product1);
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(product.getName());
        product.setImageUrl(product.getImageUrl());
        product.setPrice(product.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory().getName());
        category.setDescription(productDto.getCategory().getDescription());
        product.setCategory(category);
        return product;
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(product.getCategory().getName());
        productDto.setCategory(categoryDto);
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
