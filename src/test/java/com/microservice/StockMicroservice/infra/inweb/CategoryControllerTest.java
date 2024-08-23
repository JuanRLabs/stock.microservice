package com.microservice.StockMicroservice.infra.inweb;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.aplication.port.in.category.IListAllCategoryPort;
import com.microservice.StockMicroservice.aplication.usecase.CreateCategoryServiceImpl;
import com.microservice.StockMicroservice.aplication.usecase.ListCategoryServiceImpl;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.domain.enums.APIError;
import com.microservice.StockMicroservice.domain.exceptions.EmazonExceptions;
import com.microservice.StockMicroservice.infraestructura.in.web.CategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
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
    private CreateCategoryServiceImpl createCategoryService;

    @InjectMocks
    private CategoryController categoryController;

    private CreateCategoryCommon createCategoryRequest;
    private CategoryDomain categoryDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createCategoryRequest = new CreateCategoryCommon("Electronics", "All kinds of electronic gadgets");
        categoryDomain = new CategoryDomain(1L, "Electronics", "All kinds of electronic gadgets");
    }

    @Test
    void create_ShouldReturnAcceptedStatus_WhenRequestIsValid() {
        when(createCategoryService.create(any(CreateCategoryCommon.class))).thenReturn(categoryDomain);

        ResponseEntity<CategoryDomain> responseEntity = categoryController.create(createCategoryRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(categoryDomain, responseEntity.getBody());
    }

    @Test
    void create_ShouldThrowEmazonExceptions_WhenRequestIsInvalid() {
        CreateCategoryCommon invalidRequest = new CreateCategoryCommon(null, "No name");

        EmazonExceptions exception = assertThrows(EmazonExceptions.class, () -> {
            categoryController.create(invalidRequest);
        });

        assertEquals(APIError.BAD_FORMAT, APIError.BAD_FORMAT);
    }

    @Mock
    private ListCategoryServiceImpl listCategoryService;


    @Test
    void listALL_ShouldReturnPageOfCategories_WhenParamsAreValid() {
        // Arrange
        int page = 0;
        int size = 10;
        String sort = "asc";

        CategoryDomain category1 = new CategoryDomain(1L, "Electronics", "Various electronic items");
        CategoryDomain category2 = new CategoryDomain(2L, "Books", "Various kinds of books");

        Page<CategoryDomain> expectedPage = new PageImpl<>(Arrays.asList(category1, category2));
        when(listCategoryService.ListAll(page, size, sort)).thenReturn(expectedPage);

        // Act
        ResponseEntity<Page<CategoryDomain>> response = categoryController.listALL(page, size, sort);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(listCategoryService, times(1)).ListAll(page, size, sort);
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
        verify(listCategoryService, never()).ListAll(anyInt(), anyInt(), anyString());
    }

}