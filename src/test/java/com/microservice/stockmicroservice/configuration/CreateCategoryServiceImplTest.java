package com.microservice.stockmicroservice.configuration;

import com.microservice.stockmicroservice.domain.api.usecase.CategoryUseCase;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.enums.APIError;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
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
    private CategoryUseCase createCategoryService;

    private CreateCategoryInput validCategoryRequest;
    private Category validCategoryDomain;
    private CategoryEntity savedCategoryEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validCategoryRequest = new CreateCategoryInput("Electronics", "Various electronic items");
        validCategoryDomain = Category.builder()
                .id(null)
                .name("Electronics")
                .description("various electronic items")
                .build();

        savedCategoryEntity = new CategoryEntity(1L, "Electronics", "various electronic items");
    }

    @Test
    void create_ShouldReturnCategoryDomain_WhenCategoryIsValidAndDoesNotExist() {
        when(categoryPersistenceAdapter.categoryExist(validCategoryRequest.getName())).thenReturn(false);
        when(categoryPersistenceAdapter.save(any(Category.class))).thenReturn(savedCategoryEntity);
        when(categoryMapper.entityToDomain(savedCategoryEntity)).thenReturn(validCategoryDomain);

        Category response = createCategoryService.create(validCategoryRequest);

        assertNotNull(response);
        assertEquals(validCategoryDomain.getName(), response.getName());
        assertEquals(validCategoryDomain.getDescription(), response.getDescription());
        verify(categoryPersistenceAdapter, times(1)).save(any(Category.class));
    }

    @Test
    void create_ShouldThrowValidationError_WhenCategoryHasInvalidData() {
        CreateCategoryInput invalidRequest = new CreateCategoryInput("123", "Invalid data");

        assertThrows(EmazonExceptions.class, () -> createCategoryService.create(invalidRequest), APIError.VALIDATION_ERROR.name());
        verify(categoryPersistenceAdapter, never()).save(any(Category.class));
    }

    @Test
    void create_ShouldThrowException_WhenCategoryWithSameNameExists() {
        when(categoryPersistenceAdapter.categoryExist(validCategoryRequest.getName())).thenReturn(true);

        EmazonExceptions exception = assertThrows(EmazonExceptions.class, () -> createCategoryService.create(validCategoryRequest));

        // Verificar que el error sea del tipo APIError.CATEGORY_WITH_SAME_NAME
        assertEquals(APIError.CATEGORY_WITH_SAME_NAME.getMessage(), exception.getDescription());

        verify(categoryPersistenceAdapter, never()).save(any(Category.class));
    }
}
