package com.microservice.stockmicroservice.configuration;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.api.usecase.BrandUseCase;
import com.microservice.stockmicroservice.domain.api.usecase.CategoryUseCase;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    // Brand beans
    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }


}
