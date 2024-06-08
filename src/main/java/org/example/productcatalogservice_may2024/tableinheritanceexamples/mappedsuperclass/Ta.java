package org.example.productcatalogservice_may2024.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta")
public class Ta extends User {
    private Integer ratings;
}
