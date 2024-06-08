package org.example.productcatalogservice_may2024.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private Long numOfHours;
}
