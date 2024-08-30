package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.Brand.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void create(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }
}
