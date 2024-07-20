package org.example.productcatalogservice_may2024.services;

import org.example.productcatalogservice_may2024.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(Long id,Product product);

    Product getProductBasedOnUserScope(Long productId,Long userId);
}
