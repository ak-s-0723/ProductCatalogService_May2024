package org.example.productcatalogservice_may2024.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    private Long numOfHours;
}
