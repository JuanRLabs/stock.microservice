package com.microservice.stockmicroservice.infra.inweb;

import com.microservice.stockmicroservice.domain.api.usecase.CategoryUseCase;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.enums.APIError;
import com.microservice.stockmicroservice.adapters.driving.http.controller.CategoryRestControllerAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class CategoryControllerTest {

    @Mock
    private CategoryUseCase createCategoryService;

    @InjectMocks
    private CategoryRestControllerAdapter categoryController;

    private CreateCategoryInput createCategoryRequest;
    private Category categoryDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createCategoryRequest = new CreateCategoryInput("Electronics", "All kinds of electronic gadgets");
        categoryDomain = new Category(1L, "Electronics", "All kinds of electronic gadgets");
    }

    @Test
    void create_ShouldReturnAcceptedStatus_WhenRequestIsValid() {
        when(createCategoryService.create(any(CreateCategoryInput.class))).thenReturn(categoryDomain);

        ResponseEntity<Category> responseEntity = categoryController.create(createCategoryRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(categoryDomain, responseEntity.getBody());
    }

    @Test
    void create_ShouldThrowEmazonExceptions_WhenRequestIsInvalid() {
        CreateCategoryInput invalidRequest = new CreateCategoryInput(null, "No name");

        EmazonExceptions exception = assertThrows(EmazonExceptions.class, () -> {
            categoryController.create(invalidRequest);
        });

        assertEquals(APIError.BAD_FORMAT, APIError.BAD_FORMAT);
    }

    @Mock
    private ListCategoriesUseCase listCategoryService;


    @Test
    void listALL_ShouldReturnPageOfCategories_WhenParamsAreValid() {
        // Arrange
        int page = 0;
        int size = 10;
        String sort = "asc";

        Category category1 = new Category(1L, "Electronics", "Various electronic items");
        Category category2 = new Category(2L, "Books", "Various kinds of books");

        Page<Category> expectedPage = new PageImpl<>(Arrays.asList(category1, category2));
        when(listCategoryService.listAllCategories(page, size, sort)).thenReturn(expectedPage);

        // Act
        ResponseEntity<Page<Category>> response = categoryController.listALL(page, size, sort);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(listCategoryService, times(1)).listAllCategories(page, size, sort);
    }


    @Test
    void listALL_ShouldThrowEmazonExceptions_WhenParamsAreInvalid() {
        // Arrange
        int page = -1;
        int size = 10;
        String sort = "asc";

        // Act & Assert
        EmazonExceptions exception = assertThrows(EmazonExceptions.class, () -> {
            categoryController.listALL(page, size, sort);
        });

        assertEquals(APIError.ILLEGAL_PARAMS_REQUEST, APIError.ILLEGAL_PARAMS_REQUEST);
        verify(listCategoryService, never()).listAllCategories(anyInt(), anyInt(), anyString());
    }

}