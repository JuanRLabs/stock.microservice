package com.microservice.StockMicroservice.infraestructura.out.persistence;

import com.microservice.StockMicroservice.domain.CategoryDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity domainToEntity(CategoryDomain domain){
        return CategoryEntity.builder()
                .name(domain.getName())
                .description(domain.getDescription())
                .build();
    }
    public CategoryDomain entityToDomain(CategoryEntity entity){
        return CategoryDomain.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
