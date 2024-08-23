package com.microservice.StockMicroservice.aplication.usecase;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.aplication.port.in.category.ICreateCategoryPort;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.domain.exceptions.EmazonExceptions;
import com.microservice.StockMicroservice.domain.enums.APIError;
import com.microservice.StockMicroservice.domain.utilityClass.StringUtilsEmazon;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryEntity;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryMapper;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryPersistenceAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCategoryServiceImpl implements ICreateCategoryPort {

    //uso del repositorio JPA
    private final CategoryPersistenceAdapter categoryPersistenceAdapter;
    private final CategoryMapper categoryMapper;

    //Method constructor de la propiedad
    public CreateCategoryServiceImpl( CategoryPersistenceAdapter categoryPersistenceAdapter,
                                      CategoryMapper categoryMapper) {
        this.categoryPersistenceAdapter = categoryPersistenceAdapter;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @Override
    public CategoryDomain create(CreateCategoryCommon common) {
        boolean resp = categoryPersistenceAdapter.categoryExist(common.getName().trim());
        if (!resp) {
            if ((StringUtilsEmazon.isValidLength(common.getName(),50) && StringUtilsEmazon.isValidLength(common.getDescription(), 90))
                && StringUtilsEmazon.isAlphabetic(common.getName()))
            {
                CategoryDomain data = CategoryDomain.builder()
                        .id(null)
                        .name(common.getName())
                        .description(common.getDescription().trim().toLowerCase())
                        .build();
                //persistencia
                CategoryEntity response = categoryPersistenceAdapter.save(data);
                CategoryDomain responseData = null;
                return responseData = categoryMapper.entityToDomain(response);
            }
                throw new EmazonExceptions(APIError.VALIDATION_ERROR);
        }else {
            throw new EmazonExceptions(APIError.CATEGORY_WITH_SAME_NAME);
        }
    }
}


