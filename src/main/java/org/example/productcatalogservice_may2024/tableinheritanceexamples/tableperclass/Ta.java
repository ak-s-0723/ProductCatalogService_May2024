package org.example.productcatalogservice_may2024.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User {
    private Integer ratings;
}
