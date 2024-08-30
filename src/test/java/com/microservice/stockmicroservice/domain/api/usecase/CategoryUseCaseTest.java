package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock(lenient = true)
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    public CategoryUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategorySuccessfully() {
        // Arrange
        Category category = new Category(null, "category new", "description new");

        // Act
        categoryUseCase.create(category);

        // Assert
        verify(categoryPersistencePort, times(1)).create(any(Category.class));
        verifyNoMoreInteractions(categoryPersistencePort);
    }

    @Test
    void testListAllCategories() {
        // Arrange
        PageableRequest pageableRequest = new PageableRequest(1, 5, "name", Sorted.ASC);

        List<Category> categories = List.of(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        Paginated<Category> paginatedCategories = new Paginated<>(
                categories, 1, categories.size()
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

    @Test
    void testCreateCategoryWithInvalidDataThrowsException() {
        // Arrange
        Category invalidCategory = new Category(null, "", "description new");

        doThrow(new EmptyFieldException("Name cannot be empty"))
                .when(categoryPersistencePort).create(any(Category.class));

        // Act & Assert
        assertThrows(EmptyFieldException.class, () -> categoryUseCase.create(invalidCategory));
        verify(categoryPersistencePort, times(1)).create(any(Category.class));
        verifyNoMoreInteractions(categoryPersistencePort);
    }

    @Test
    void testListAllCategoriesReturnsEmptyList() {
        // Arrange
        PageableRequest pageableRequest = new PageableRequest(1, 5, "name", Sorted.ASC);
        List<Category> categories = List.of(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );
        Paginated<Category> paginatedCategories = new Paginated<>(categories, 1, categories.size());

        Paginated<Category> emptyPaginatedCategories = null;
        when(categoryPersistencePort.listAllCategories(any(PageableRequest.class)))
                .thenReturn(emptyPaginatedCategories);

        // Act
        Paginated<Category> result = categoryUseCase.listAllCategories(pageableRequest);

        // Assert
        verify(categoryPersistencePort, times(1)).listAllCategories(any(PageableRequest.class));
        verifyNoMoreInteractions(categoryPersistencePort);
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getTotalPages());
    }

}