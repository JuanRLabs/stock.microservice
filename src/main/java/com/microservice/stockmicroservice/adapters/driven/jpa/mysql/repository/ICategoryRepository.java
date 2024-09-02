package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);

}
