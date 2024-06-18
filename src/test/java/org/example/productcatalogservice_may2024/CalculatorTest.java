package org.example.productcatalogservice_may2024;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorTest {

    private Calculator calculator;

    //TestFunctionName_When_Then
    @Test
    void TestAdd_TwoIntegers_RunsSuccessfully() {
        //Arrange
        calculator = new Calculator();

        //Act
        int result = calculator.add(100,101);

        //Assert
        assertEquals(201,result);
    }

    @Test
    void TestDivide_ByZero_ThrowsArithmeticException() {
        //Arrange
        calculator = new Calculator();

        //Act & Assert
       assertThrows(ArithmeticException.class,
               ()->calculator.divide(1,0));
    }
}