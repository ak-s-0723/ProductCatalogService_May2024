package org.example.productcatalogservice_may2024.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
        public String hello() {
           return "SpringBoot";
        }
}
