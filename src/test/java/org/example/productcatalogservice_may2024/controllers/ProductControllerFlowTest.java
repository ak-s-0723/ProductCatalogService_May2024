package org.example.productcatalogservice_may2024.controllers;

import org.example.productcatalogservice_may2024.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {

    @Autowired
    private ProductController productController;

    //@Test
    public void Test_Create_Replace_GetProduct_RunsSuccessfully() {
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setName("Macbook");
        productDto.setId(5L);

        //Act
        ProductDto response = productController.createProduct(productDto);
        ProductDto productDtoResponseEntity =
                productController.getProduct(productDto.getId());

        productDto.setName("Macbook Pro");

        ProductDto response2 = productController
                .replaceProduct(productDtoResponseEntity.getId()
                        ,productDto);

        ProductDto productDtoResponseEntity2 =
                productController.getProduct(productDto.getId());

        //Assert
        assertEquals("Macbook",productDtoResponseEntity.getName());
        assertEquals("Macbook Pro",productDtoResponseEntity2.getName());
    }
}
