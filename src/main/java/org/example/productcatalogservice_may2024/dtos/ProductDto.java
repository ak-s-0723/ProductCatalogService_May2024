package org.example.productcatalogservice_may2024.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_may2024.models.Category;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    private CategoryDto category;
}
