package com.microservice.stockmicroservice.configuration;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.api.usecase.CategoryUseCase;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}
