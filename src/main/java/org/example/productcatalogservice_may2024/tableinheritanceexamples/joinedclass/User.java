package org.example.productcatalogservice_may2024.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name="jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    private Long id;

    private String name;

    private String email;
}
