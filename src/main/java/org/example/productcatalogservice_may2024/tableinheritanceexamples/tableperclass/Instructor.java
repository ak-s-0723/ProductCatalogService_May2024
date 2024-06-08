package org.example.productcatalogservice_may2024.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructor")
public class Instructor extends User {
    private String company;
}
