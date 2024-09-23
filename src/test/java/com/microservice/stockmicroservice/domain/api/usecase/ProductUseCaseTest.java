package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalBrandException;
import com.microservice.stockmicroservice.domain.exceptions.ProductAlreadyExistsException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductUseCaseTest {

    @Mock
    private IProductPersistencePort productPersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private ProductUseCase productUseCase;

    private Product validProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Brand brand = new Brand(1L, "ValidBrand", "Description");
        List<Category> categories = Arrays.asList(new Category(1L, "Category1", "description1"),
        new Category(2L, "Category2", "description2"));
        validProduct = new Product(1L, "ValidProduct", "Description", 10L, BigDecimal.valueOf(100.000), brand, categories);
    }

    @Test
    void create_ShouldThrowProductAlreadyExistsException_WhenProductExists() {
        when(brandPersistencePort.existsBrand(validProduct.getBrand().getId())).thenReturn(true);
        when(productPersistencePort.findByName(validProduct.getName().trim())).thenReturn(validProduct);

        assertThrows(ProductAlreadyExistsException.class, () -> {
            productUseCase.create(validProduct);
        });
    }

    @Test
    void create_ShouldThrowEmptyFieldException_WhenCategoriesContainDuplicateIds() {
        validProduct.setCategoriesId(Arrays.asList(
                new Category(1L, "Category1", "category1"),
                new Category(1L, "DuplicateCategory" , "DuplicateCategory")));

        when(brandPersistencePort.existsBrand(validProduct.getBrand().getId())).thenReturn(true);
        when(productPersistencePort.findByName(validProduct.getName().trim())).thenReturn(null);

        assertThrows(EmptyFieldException.class, () -> {
            productUseCase.create(validProduct);
        });
    }

}