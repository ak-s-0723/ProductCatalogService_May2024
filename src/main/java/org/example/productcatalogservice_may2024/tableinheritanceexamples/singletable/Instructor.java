package org.example.productcatalogservice_may2024.tableinheritanceexamples.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="st_instructor")
@DiscriminatorValue(value ="3")
public class Instructor extends User {
    private String company;
}
