package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);
    Page<CategoryEntity> findAll(Pageable pageable);

}
