package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryProductEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryProductRepository;
import com.microservice.stockmicroservice.domain.spi.ICategoryProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryProductAdapter implements ICategoryProductPersistencePort {
    private final ICategoryProductRepository categoryProductRepository;


    @Override
    public void createRelationsCategories(List<Long> categoriesId, Long productId) {
        categoriesId.forEach(categoryId ->  categoryProductRepository.save(new CategoryProductEntity(categoryId, productId)));
    }
}
