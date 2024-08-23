package com.microservice.StockMicroservice.aplication.usecase;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.domain.enums.APIError;
import com.microservice.StockMicroservice.domain.exceptions.EmazonExceptions;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryEntity;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryMapper;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryPersistenceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateCategoryServiceImplTest {

    @Mock
    private CategoryPersistenceAdapter categoryPersistenceAdapter;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CreateCategoryServiceImpl createCategoryService;

    private CreateCategoryCommon validCategoryRequest;
    private CategoryDomain validCategoryDomain;
    private CategoryEntity savedCategoryEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validCategoryRequest = new CreateCategoryCommon("Electronics", "Various electronic items");
        validCategoryDomain = CategoryDomain.builder()
                .id(null)
                .name("Electronics")
                .description("various electronic items")
                .build();

        savedCategoryEntity = new CategoryEntity(1L, "Electronics", "various electronic items");
    }

    @Test
    void create_ShouldReturnCategoryDomain_WhenCategoryIsValidAndDoesNotExist() {
        when(categoryPersistenceAdapter.categoryExist(validCategoryRequest.getName())).thenReturn(false);
        when(categoryPersistenceAdapter.save(any(CategoryDomain.class))).thenReturn(savedCategoryEntity);
        when(categoryMapper.entityToDomain(savedCategoryEntity)).thenReturn(validCategoryDomain);

        CategoryDomain response = createCategoryService.create(validCategoryRequest);

        assertNotNull(response);
        assertEquals(validCategoryDomain.getName(), response.getName());
        assertEquals(validCategoryDomain.getDescription(), response.getDescription());
        verify(categoryPersistenceAdapter, times(1)).save(any(CategoryDomain.class));
    }

    @Test
    void create_ShouldThrowValidationError_WhenCategoryHasInvalidData() {
        CreateCategoryCommon invalidRequest = new CreateCategoryCommon("123", "Invalid data");

        assertThrows(EmazonExceptions.class, () -> createCategoryService.create(invalidRequest), APIError.VALIDATION_ERROR.name());
        verify(categoryPersistenceAdapter, never()).save(any(CategoryDomain.class));
    }

    @Test
    void create_ShouldThrowException_WhenCategoryWithSameNameExists() {
        when(categoryPersistenceAdapter.categoryExist(validCategoryRequest.getName())).thenReturn(true);

        EmazonExceptions exception = assertThrows(EmazonExceptions.class, () -> createCategoryService.create(validCategoryRequest));

        // Verificar que el error sea del tipo APIError.CATEGORY_WITH_SAME_NAME
        assertEquals(APIError.CATEGORY_WITH_SAME_NAME.getMessage(), exception.getDescription());

        verify(categoryPersistenceAdapter, never()).save(any(CategoryDomain.class));
    }
}
