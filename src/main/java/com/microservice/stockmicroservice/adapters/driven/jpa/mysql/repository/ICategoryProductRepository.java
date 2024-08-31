package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryProductRepository extends JpaRepository<CategoryProductEntity, Long> {
    @Override
    <S extends CategoryProductEntity> List<S> saveAll(Iterable<S> entities);
}
