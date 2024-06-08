package org.example.productcatalogservice_may2024.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private String company;
}
