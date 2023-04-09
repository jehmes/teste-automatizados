package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("SELECT count(1) FROM Product p WHERE p.name IN (:productsNames)")
    Integer findByProductsName(@Param("productsNames") List<String> productsNames);
}
