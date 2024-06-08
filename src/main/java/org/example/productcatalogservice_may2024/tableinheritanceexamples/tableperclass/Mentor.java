package org.example.productcatalogservice_may2024.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User {
    private Long numOfHours;
}
