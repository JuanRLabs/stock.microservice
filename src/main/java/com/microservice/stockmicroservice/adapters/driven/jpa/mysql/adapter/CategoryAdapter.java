package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.utilityClass.DomainConstants;
import com.microservice.stockmicroservice.domain.utilityClass.StringUtilsEmazon;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void create(Category category) {
        if (!categoryRepository.existsByName(category.getName())) {
            if ((StringUtilsEmazon.isValidLength(category.getName(), 50) && StringUtilsEmazon.isValidLength(category.getDescription(), 90))
                    && StringUtilsEmazon.isAlphabetic(category.getName())) {
                Category data = new Category(null, category.getName(), category.getDescription());
                categoryRepository.save(categoryEntityMapper.toEntity(data));
            }else{throw new EmptyFieldException(DomainConstants.FIELD_NAME_OR_DESCRIPTION_NULL_MESSAGE);}
        }else {throw new CategoryAlreadyExistsException();}
    }

    @Override
    public Page<Category> listAllCategories(Pageable pageable) {
        return categoryEntityMapper.toModelList(categoryRepository.findAll(pageable));
    }
}
