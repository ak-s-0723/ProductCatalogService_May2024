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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
