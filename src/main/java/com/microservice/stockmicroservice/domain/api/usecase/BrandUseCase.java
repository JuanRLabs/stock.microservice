package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.Brand.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void create(Brand brand) {
        brandPersistencePort.create(brand);
    }
}
