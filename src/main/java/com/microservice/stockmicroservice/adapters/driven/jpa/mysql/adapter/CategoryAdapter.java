package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.util.DomainConstants;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;
import com.microservice.stockmicroservice.domain.util.StringUtilsEmazon;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Paginated<Category> listAllCategories(PageableRequest pageableRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageableRequest.getSorted().name()), pageableRequest.getSort());
        Pageable pageable = PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize(), sort);
        Page<CategoryEntity> responseRepository = categoryRepository.findAll(pageable);
        List<Category> cagories = categoryEntityMapper.toModelList(responseRepository);
        return  new Paginated<Category>(cagories, responseRepository.getTotalPages(), responseRepository.getTotalElements());
    }
}
