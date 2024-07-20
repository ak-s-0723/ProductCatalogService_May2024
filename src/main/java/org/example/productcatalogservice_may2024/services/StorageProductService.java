package org.example.productcatalogservice_may2024.services;

import org.example.productcatalogservice_may2024.dtos.UserDto;
import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductBasedOnUserScope(Long productId,Long userId) {
        Product product = productRepository.findById(productId).get();

        UserDto userDto = restTemplate.getForEntity("http://userservice/users/{id}", UserDto.class,userId).getBody();
        System.out.println(userDto.getEmail());
        if(userDto == null) {
            return null;
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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
