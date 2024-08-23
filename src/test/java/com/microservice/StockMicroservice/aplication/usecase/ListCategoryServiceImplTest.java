package com.microservice.StockMicroservice.aplication.usecase;

import org.junit.jupiter.api.Test;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryPersistenceAdapter;
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
    private ListCategoryServiceImpl listCategoryService;

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

        CategoryDomain category1 = new CategoryDomain(1L, "Electronics", "Various electronic items");
        CategoryDomain category2 = new CategoryDomain(2L, "Books", "Various kinds of books");

        List<CategoryDomain> categories = Arrays.asList(category1, category2);
        Page<CategoryDomain> expectedPage = new PageImpl<>(categories);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        when(categoryPersistenceAdapter.findAllCategories(pageable)).thenReturn(expectedPage);

        // Act
        Page<CategoryDomain> resultPage = listCategoryService.ListAll(page, size, sort);

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

        CategoryDomain category1 = new CategoryDomain(1L, "Beauty", "Various kinds of costemtics");
        CategoryDomain category2 = new CategoryDomain(2L, "Electronics", "Various electronic items");

        List<CategoryDomain> categories = Arrays.asList(category1, category2);
        Page<CategoryDomain> expectedPage = new PageImpl<>(categories);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
        when(categoryPersistenceAdapter.findAllCategories(pageable)).thenReturn(expectedPage);

        // Act
        Page<CategoryDomain> resultPage = listCategoryService.ListAll(page, size, sort);

        // Assert
        assertEquals(expectedPage, resultPage);
        assertEquals(2, resultPage.getTotalElements());
        verify(categoryPersistenceAdapter, times(1)).findAllCategories(pageable);
    }
}
