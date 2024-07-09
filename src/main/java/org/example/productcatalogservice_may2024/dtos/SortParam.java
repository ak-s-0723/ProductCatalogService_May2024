package org.example.productcatalogservice_may2024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String paramName;

    private SortType sortType;
}
