package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryProductRepository extends JpaRepository<CategoryProductEntity, Long> {
}
