package com.microservice.StockMicroservice.infraestructura.out.persistence;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.name LIKE :nombre")
    CategoryEntity findByName(@Param("nombre") String request);

}
