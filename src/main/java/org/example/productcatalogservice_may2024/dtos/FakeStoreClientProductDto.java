package org.example.productcatalogservice_may2024.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreClientProductDto implements Serializable {
    private String title;
    private Long id;
    private String description;
    private String image;
    private String category;
    private Double price;
    //private FakeStoreRatingDto fakeStoreRatingDto;
}
