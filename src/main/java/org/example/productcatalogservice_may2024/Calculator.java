package org.example.productcatalogservice_may2024;

public class Calculator {

    public int add(int num1,int num2) {
        return num1+num2;
    }

    public int divide(int num1,int num2) {
        try {
            return num1/num2;
        } catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
