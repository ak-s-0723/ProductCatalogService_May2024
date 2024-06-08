package org.example.productcatalogservice_may2024.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_instructor")
public class Instructor extends User {
    private String company;
}
