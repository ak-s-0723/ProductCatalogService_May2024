package org.example.productcatalogservice_may2024.repositories;

import org.example.productcatalogservice_may2024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    //List<Product> findByName(String query, Pageable pageable);
    Page<Product> findByName(String query, Pageable pageable);
    Optional<Product> findById(Long id);

    Product save(Product product);

    List<Product> findProductByPriceBetween(Double low, Double high);

    List<Product> findAllByIsPrime(Boolean val);
    List<Product> findAllByIsPrimeTrue();

    List<Product> findAllByOrderByPriceDesc();

    @Query("SELECT p.name from Product p WHERE p.id=?1")
    String findProductNameById(Long id);

    @Query("SELECT c.name from Product p join Category c on p.category.id=c.id WHERE p.id=:id")
    String findCategoryNameFromProductId(@Param("id") Long id);


}
