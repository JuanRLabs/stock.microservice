package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryProductEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryProductRepository;
import com.microservice.stockmicroservice.domain.spi.ICategoryProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryProductAdapter implements ICategoryProductPersistencePort {
    private final ICategoryProductRepository categoryProductRepository;

    @Override
    public void createRelationsCategories(List<Long> categoryIds, Long productId) {
        List<CategoryProductEntity> relations = categoryIds.stream()
                .map(categoryId -> new CategoryProductEntity(categoryId, productId))
                .collect(Collectors.toList());
                categoryProductRepository.saveAll(relations);
    }
}
