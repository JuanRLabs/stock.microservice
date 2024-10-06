package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.exceptions.CategoryAlreadyExistsException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentDescriptionException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentNameException;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;


import com.microservice.stockmicroservice.domain.util.InputValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private InputValidate inputValidate;

    @InjectMocks
    private CategoryUseCase categoryService;

    @Test
    void testCreateCategory_CategoryAlreadyExists() {
        // Given
        Category category = new Category(null, "Existing Name", "Valid Description");
        when(categoryPersistencePort.existsByName(category.getName().trim())).thenReturn(true);

        // When & Then
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.create(category));
    }


}