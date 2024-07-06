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

    //@Test
    //@Transactional
    public void testFetchModes() {
        Category category = categoryRepo.findById(10L).get();
        System.out.println(category.getName());
        List<Product> productList = category.getProductList();
        for(Product p : productList) {
            System.out.println(p.getName());
        }
    }

    @Test
    @Transactional
    public void nPlusOneProblem() {
        List<Category> categories = categoryRepo.findAll();
        for(Category c : categories ) {
            List<Product> p = c.getProductList();
//            if(!p.isEmpty()) {
//                System.out.println(p.get(0).getName());
//            }
        }
    }
}