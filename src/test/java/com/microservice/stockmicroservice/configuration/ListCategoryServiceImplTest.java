package com.microservice.stockmicroservice.configuration;

import org.junit.jupiter.api.Test;
import com.microservice.stockmicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ListCategoryServiceImplTest {

    @Mock
    private CategoryPersistenceAdapter categoryPersistenceAdapter;

    @InjectMocks
    private ListCategoriesUseCase listCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll_ShouldReturnPageOfCategories_WhenInvoked() {
        // Arrange
        int page = 0;
        int size = 10;
        String sort = "asc";

        Category category1 = new Category(1L, "Electronics", "Various electronic items");
        Category category2 = new Category(2L, "Books", "Various kinds of books");

        List<Category> categories = Arrays.asList(category1, category2);
        Page<Category> expectedPage = new PageImpl<>(categories);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        when(categoryPersistenceAdapter.findAllCategories(pageable)).thenReturn(expectedPage);

        // Act
        Page<Category> resultPage = listCategoryService.listAllCategories(page, size, sort);

        // Assert
        assertEquals(expectedPage, resultPage);
        assertEquals(2, resultPage.getTotalElements());
        verify(categoryPersistenceAdapter, times(1)).findAllCategories(pageable);
    }

    @Test
    void listAll_ShouldReturnPageOfCategories_WhenSortIsDesc() {
        // Arrange
        int page = 0;
        int size = 10;
        String sort = "desc";

        Category category1 = new Category(1L, "Beauty", "Various kinds of costemtics");
        Category category2 = new Category(2L, "Electronics", "Various electronic items");

        List<Category> categories = Arrays.asList(category1, category2);
        Page<Category> expectedPage = new PageImpl<>(categories);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
        when(categoryPersistenceAdapter.findAllCategories(pageable)).thenReturn(expectedPage);

        // Act
        Page<Category> resultPage = listCategoryService.listAllCategories(page, size, sort);

        // Assert
        assertEquals(expectedPage, resultPage);
        assertEquals(2, resultPage.getTotalElements());
        verify(categoryPersistenceAdapter, times(1)).findAllCategories(pageable);
    }
}
