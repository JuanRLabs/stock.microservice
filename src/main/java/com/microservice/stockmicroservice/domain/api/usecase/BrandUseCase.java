package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentDescriptionException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentNameException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.Brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.util.StringUtilsEmazon;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void create(Brand brand) {
        if (StringUtilsEmazon.isEmpty(brand.getName())
                || !StringUtilsEmazon.isValidLength(brand.getName(), 50)
                || !StringUtilsEmazon.isAlphabetic(brand.getName())) {
            throw new IllegalArgumentNameException();
        } else if (StringUtilsEmazon.isEmpty(brand.getDescription())
                || !StringUtilsEmazon.isValidLength(brand.getDescription(), 120)) {
            throw new IllegalArgumentDescriptionException();
        }else {
            brandPersistencePort.create(brand);
        }
    }

}
