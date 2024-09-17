package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void createCategorySuccessfully() {
        // Given
        Category category = new Category(null, "category new", "description new");

        // When
        categoryUseCase.create(category);

        // Then
        verify(categoryPersistencePort, times(1)).create(any(Category.class));
    }

    @Test
    void testListAllCategoriesSuccessfully() {
        // Arrange
        PageableRequest pageableRequest = new PageableRequest(1, 1, "name", Sorted.ASC);

        List<Category> categories = List.of(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        Paginated<Category> paginatedCategories = new Paginated<>(
                categories, pageableRequest, 1, categories.size()
        );


        when(categoryPersistencePort.listAllCategories(any(PageableRequest.class)))
                .thenReturn(paginatedCategories);

        // Act
        Paginated<Category> result = categoryUseCase.listAllCategories(pageableRequest);

        // Assert
        verify(categoryPersistencePort, times(1)).listAllCategories(any(PageableRequest.class));
        verifyNoMoreInteractions(categoryPersistencePort);
        assertEquals(paginatedCategories, result);
    }

}