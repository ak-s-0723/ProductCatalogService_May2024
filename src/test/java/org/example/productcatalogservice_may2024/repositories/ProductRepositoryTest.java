package org.example.productcatalogservice_may2024.repositories;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_may2024.models.Category;
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

    @Test
    public void saveDataInAwsDb() {
        Product product = new Product();
        product.setId(1L);
        product.setName("XXX");
        product.setDescription("private");
        product.setPrice(99D);
        Category category = new Category();
        category.setId(1L);
        category.setName("NAUGHTY");
        category.setDescription("nhi bataonga");
        product.setCategory(category);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setId(10L);
        product2.setName("Iphone 15");
        product2.setDescription("Most Powerful Iphone ever");
        product2.setPrice(10000D);
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Iphones");
        product2.setCategory(category2);
        productRepository.save(product2);
    }

}