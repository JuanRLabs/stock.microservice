package com.microservice.StockMicroservice.aplication.usecase;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.aplication.port.in.category.ICreateCategoryPort;
import com.microservice.StockMicroservice.domain.CategoryDomain;
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
    public CreateCategoryServiceImpl( CategoryPersistenceAdapter categoryPersistenceAdapter, CategoryMapper categoryMapper) {
        this.categoryPersistenceAdapter = categoryPersistenceAdapter;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @Override
    public CategoryDomain create(CreateCategoryCommon common) {
        //Validación que no se repita una categoria
        boolean resp = categoryPersistenceAdapter.categoryExist(common.getName());
        CategoryEntity response = null;
        CategoryDomain responseData = null;
        if (!resp) {
            CategoryDomain data = CategoryDomain.builder()
                    .id(null)
                    .name(common.getName())
                    .description(common.getDescription())
                    .build();
            //persistencia
            response = categoryPersistenceAdapter.save(data);
        }
        return responseData = categoryMapper.entityToDomain(response);
        // este return va dentro del if.
        //AQUI VA EL MANEJO DE EXCEPCION, EN CASO QUE EXSTA, LANZAR UNA.
    }

}
