package org.example.productcatalogservice_may2024.services;

import org.example.productcatalogservice_may2024.models.Product;

import java.util.List;

public interface IFakeStoreProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);
}
