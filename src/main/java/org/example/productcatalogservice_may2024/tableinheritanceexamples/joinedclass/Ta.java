package org.example.productcatalogservice_may2024.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User {
    private Integer ratings;
}
