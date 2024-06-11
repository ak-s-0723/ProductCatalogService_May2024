package org.example.productcatalogservice_may2024.repositories;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_may2024.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testQueries() {
        //List<Product> productList = productRepository.findProductByPriceBetween(75999D,148000D);
       /* List<Product> productList = productRepository.findAllByOrderByPriceDesc();
        for(Product p : productList) {
            System.out.println(p.getName());
        }*/

        //System.out.println(productRepository.findProductNameById(1L));
        System.out.println(productRepository.findCategoryNameFromProductId(1L));

    }

}