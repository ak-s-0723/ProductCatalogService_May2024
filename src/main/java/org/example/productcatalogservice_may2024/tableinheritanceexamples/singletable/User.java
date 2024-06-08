package org.example.productcatalogservice_may2024.tableinheritanceexamples.singletable;

import jakarta.persistence.*;

@Entity(name="st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",discriminatorType = DiscriminatorType.INTEGER)
public class User {

    @Id
    private Long id;

    private String name;

    private String email;
}
