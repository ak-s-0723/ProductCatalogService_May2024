package org.example.productcatalogservice_may2024.controllers;

import org.example.productcatalogservice_may2024.dtos.ProductDto;
import org.example.productcatalogservice_may2024.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        product.setDescription("fastest iphone ever");
        return product;
    }

    @PostMapping
    public Product createProduct(ProductDto productDto) {
        return null;
    }
}
