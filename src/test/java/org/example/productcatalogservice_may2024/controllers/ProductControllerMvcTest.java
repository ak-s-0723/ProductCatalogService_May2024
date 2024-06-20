package org.example.productcatalogservice_may2024.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_may2024.dtos.ProductDto;
import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    //object->json->string
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        //Arrange
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Iphone16");
        product.setId(16L);
        product.setDescription("fanciest Iphone ever");
        productList.add(product);

        Product product2 = new Product();
        product2.setName("Iphone18");
        product2.setId(18L);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        //Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper
                                .writeValueAsString(productList)))
                .andExpect(jsonPath("$[0].name").value("Iphone16"))
                .andExpect(jsonPath("$[0].length()").value(3))
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void Test_CreateProduct_ProductCreatedSuccessfully() throws Exception {
        //Arrange
        Product expectedProduct = new Product();
        expectedProduct.setName("Iphone 20");
        expectedProduct.setId(20L);

        ProductDto expectedProductDto = new ProductDto();
        expectedProductDto.setName("Iphone 20");
        expectedProductDto.setId(20L);

        when(productService.createProduct(any(Product.class)))
                .thenReturn(expectedProduct);

        //Act and Assert
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                        .writeValueAsString(expectedProductDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper
                        .writeValueAsString(expectedProductDto)))
                .andExpect(jsonPath("$.name").value("Iphone 20"))
                .andExpect(jsonPath("$.length()").value(2));

    }
}
