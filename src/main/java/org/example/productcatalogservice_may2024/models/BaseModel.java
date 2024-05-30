package org.example.productcatalogservice_may2024.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private Long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private State state;
}
