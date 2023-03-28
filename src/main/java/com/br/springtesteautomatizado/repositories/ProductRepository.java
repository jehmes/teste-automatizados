package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "QUERY...", nativeQuery = true)
    Optional<Product> findByNames(List<Product> productList);
}
