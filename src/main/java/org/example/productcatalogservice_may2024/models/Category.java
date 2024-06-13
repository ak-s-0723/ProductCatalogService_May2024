package org.example.productcatalogservice_may2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;

    private String description;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size = 5)
    private List<Product> productList = new ArrayList<>();
}


//N = 5
//N = 2(BS) < 5
//2,2,1 = 3
//1+3
//
//BS=8
//8 > 5
//1 + 1
//
//BS= 5
//1+1
