package org.example.productcatalogservice_may2024.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_may2024.models.Category;

@Getter
@Setter
public class ProductDto {
    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    private CategoryDto category;
}
