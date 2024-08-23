package com.microservice.StockMicroservice.aplication.usecase;

import com.microservice.StockMicroservice.aplication.port.in.category.IListAllCategoryPort;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryMapper;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryPersistenceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListCategoryServiceImpl implements IListAllCategoryPort {

    private final CategoryPersistenceAdapter categoryPersistenceAdapter;
    private final CategoryMapper categoryMapper;

    public ListCategoryServiceImpl(CategoryPersistenceAdapter categoryPersistenceAdapter,
                                   CategoryMapper categoryMapper) {
        this.categoryPersistenceAdapter = categoryPersistenceAdapter;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<CategoryDomain> ListAll(int page, int size, String sort){
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sort)){
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "name"));
        return categoryPersistenceAdapter.findAllCategories(pageable);
    }

}
