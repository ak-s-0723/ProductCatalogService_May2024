package org.example.productcatalogservice_may2024.services;

import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
       Product productResponse = productRepository.save(product);
       return productResponse;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
