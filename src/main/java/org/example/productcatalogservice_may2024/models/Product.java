package org.example.productcatalogservice_may2024.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private Boolean isPrime;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    //private Boolean isPrime;
}


//product : category