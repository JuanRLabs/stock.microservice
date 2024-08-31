package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final IBrandRepository brandRepository;


    @Override
    public void create(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public boolean existsBrand(Long id) {
        return brandRepository.existsById(id);
    }

    @Override
    public Product findByName(String name) {
        return productEntityMapper.toModelCreated(productRepository.findByName(name));
    }
}
