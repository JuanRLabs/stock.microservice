package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.BrandAlreadyExistsException;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.Brand.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void create(Brand brand) {
        if (!brandRepository.existsByName(brand.getName())) {
            brandRepository.save(brandEntityMapper.toEntity(brand));
            }else {throw new BrandAlreadyExistsException();}
    }
}
