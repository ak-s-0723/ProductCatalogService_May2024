package org.example.productcatalogservice_may2024.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
public class SearchRequestDto {
    private String query;

    private Integer pageSize;

    private Integer pageNumber;

    private List<SortParam> sortParams= new ArrayList<>();
}
