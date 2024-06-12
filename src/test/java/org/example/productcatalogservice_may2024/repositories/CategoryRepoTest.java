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
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testLoading() {
        Category category = categoryRepo.findById(1L).get();
        System.out.println(category.getName());
        List<Product> productList = category.getProductList();
       for(Product p : productList) {
            System.out.println(p.getName());
       }
    }

    //On fetchMode.join , same result as FetchMode.select
    @Test
    @Transactional
    public void testingNPlusOne() {
        List<Category> categories = categoryRepo.findAll();
        for(Category c : categories) {
            List<Product> products = c.getProductList();
            if(!products.isEmpty()) {
                System.out.println(products.get(0).getName());
            }
        }
    }

    //Exactly same number of queries as above
    @Test
    @Transactional
    public void testingSomething() {
        List<Category> categories = categoryRepo.findAll();
        for(Category c : categories) {
            List<Product> products = c.getProductList();
            for(Product p : products) {
                System.out.println(p.getName());
            }
        }
    }
}