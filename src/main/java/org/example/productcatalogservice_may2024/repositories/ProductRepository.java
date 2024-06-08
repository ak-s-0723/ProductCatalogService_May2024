package org.example.productcatalogservice_may2024.repositories;

import org.example.productcatalogservice_may2024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    Product save(Product product);
}
