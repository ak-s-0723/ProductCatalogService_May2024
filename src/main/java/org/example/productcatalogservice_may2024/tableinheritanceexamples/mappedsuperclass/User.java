package org.example.productcatalogservice_may2024.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    private Long id;

    private String name;

    private String email;
}
