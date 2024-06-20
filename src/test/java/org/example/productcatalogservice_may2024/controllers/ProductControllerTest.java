package org.example.productcatalogservice_may2024.controllers;

import org.example.productcatalogservice_may2024.dtos.ProductDto;
import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//https://stackoverflow.com/questions/44200720/difference-between-mock-mockbean-and-mockito-mock
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;


    @MockBean
    private IProductService productService;


    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @DisplayName("returning product with id 1 successful")
    @Test
    public void Test_GetProductByID_WithValidId_ReturnsProductSuccessfully() {
       //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");
        when(productService.getProductById(any(Long.class))).thenReturn(product);

       //Act
        ResponseEntity<ProductDto> response = productController.getProduct(1L);

        //Assert
        assertNotNull(response);
        assertEquals("Iphone",response.getBody().getName());
        assertEquals(1L,response.getBody().getId());
    }

    @DisplayName("throwing exception for product with id 1")
    @Test
    public void Test_GetProductById_ExternalDependencyThrowsAnException() {
        //Arrange
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new RuntimeException("something went wrong"));

        //Act and Assert
        assertThrows(RuntimeException.class,
                ()->productController.getProduct(1L));
    }

    @DisplayName("0 id lead to illegal argument exception")
    @Test
    public void Test_GetProductById_WithInvalidId_ThrowsIllegalArgumentException() {
        //Act and Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> productController.getProduct(0L));

        assertEquals("invalid productId",ex.getMessage());

        verify(productService,times(0)).getProductById(0L);
    }

    @Test
    public void Test_ProductServiceCalledWithCorrectParameters_RunsSuccessfully() {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        when(productService.getProductById(any(Long.class))).thenReturn(product);

        //Act
        productController.getProduct(id);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(id,idCaptor.getValue());
    }
}