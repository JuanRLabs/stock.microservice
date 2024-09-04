package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import com.microservice.stockmicroservice.domain.exceptions.BrandAlreadyExistsException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentDescriptionException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentNameException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.InputValidate;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void create(Brand brand) {
        if (InputValidate.isEmpty(brand.getName())
                || !InputValidate.isValidLength(brand.getName(), 50)
                || !InputValidate.isAlphabetic(brand.getName())) {
            throw new IllegalArgumentNameException();
        }
        if (InputValidate.isEmpty(brand.getDescription())
                || !InputValidate.isValidLength(brand.getDescription(), 120)) {
            throw new IllegalArgumentDescriptionException();
        }
        if(!brandPersistencePort.existsByName(brand.getName().trim())){
            brandPersistencePort.create(brand);
        }else{
            throw new BrandAlreadyExistsException();
        }
    }

    @Override
    public Paginated<Brand> listAllBrands(PageableRequest pageableRequest) {
        return brandPersistencePort.listAllBrands(pageableRequest);
    }

}
