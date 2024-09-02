package com.microservice.stockmicroservice.domain.spi.Brand;

import com.microservice.stockmicroservice.domain.model.Brand;

public interface IBrandPersistencePort {
    void create(Brand brand);
    boolean existsByName(String name);
}
