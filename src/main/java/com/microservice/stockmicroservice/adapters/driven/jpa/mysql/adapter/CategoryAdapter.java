package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

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
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public Paginated<Category> listAllCategories(PageableRequest pageableRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageableRequest.getSorted().name()), pageableRequest.getSort());
        Pageable pageable = PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize(), sort);
        Page<CategoryEntity> responseRepository = categoryRepository.findAll(pageable);
        List<Category> categories = categoryEntityMapper.toModelList(responseRepository);
        return  new Paginated<Category>(categories, pageableRequest, responseRepository.getTotalPages(), responseRepository.getTotalElements());
    }
}
