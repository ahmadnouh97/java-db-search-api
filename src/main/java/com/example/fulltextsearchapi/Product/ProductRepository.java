package com.example.fulltextsearchapi.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE fts(:name) = true")
    List<Product> search(@Param("name") String name);
}