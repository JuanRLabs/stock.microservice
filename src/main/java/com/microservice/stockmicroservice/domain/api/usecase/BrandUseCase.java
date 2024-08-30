package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.Brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.util.DomainConstants;
import com.microservice.stockmicroservice.domain.util.StringUtilsEmazon;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void create(Brand brand) {
        if (!(StringUtilsEmazon.isValidLength(brand.getName(), 50)
                && (StringUtilsEmazon.isAlphabetic(brand.getName())))) {
            throw new IllegalArgumentException(DomainConstants.FIELD_NAME_OR_DESCRIPTION_NULL_MESSAGE);
        } else {
            brandPersistencePort.create(brand);
        }
    }

}
