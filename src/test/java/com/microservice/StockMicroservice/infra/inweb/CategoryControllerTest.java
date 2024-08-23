package com.microservice.StockMicroservice.infra.inweb;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.aplication.usecase.CreateCategoryServiceImpl;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.domain.enums.APIError;
import com.microservice.StockMicroservice.domain.exceptions.EmazonExceptions;
import com.microservice.StockMicroservice.infraestructura.in.web.CategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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

        assertEquals(APIError.BAD_FORMAT, APIError.BAD_FORMAT );
    }
}
