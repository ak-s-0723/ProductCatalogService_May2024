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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IFakeStoreProductService iFakeStoreProductService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> results = new ArrayList<>();
        List<Product> products = iFakeStoreProductService.getAllProducts();
        for(Product product : products) {
            results.add(getProductDto(product));
        }

        return results;
    }

    @GetMapping("/{id}")
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
            throw ex;
            //return handleExceptions(ex);
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ProductDto createProduct(ProductDto productDto) {
    return null;
    }


    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        Product newProduct = iFakeStoreProductService.replaceProduct(id,product);
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
