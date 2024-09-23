package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.exceptions.BrandAlreadyExistsException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentDescriptionException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentNameException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    private Brand validBrand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validBrand = new Brand(1L, "ValidBrandName", "Valid description");
    }

    @Test
    void create_ShouldCreateBrand_WhenValidInput() {
        when(brandPersistencePort.existsByName(validBrand.getName())).thenReturn(false);

        brandUseCase.create(validBrand);

        verify(brandPersistencePort, times(1)).create(validBrand);
    }

    @Test
    void create_ShouldThrowIllegalArgumentNameException_WhenNameIsEmpty() {
        validBrand.setName(" ");

        assertThrows(IllegalArgumentNameException.class, () -> brandUseCase.create(validBrand));
    }

    @Test
    void create_ShouldThrowIllegalArgumentDescriptionException_WhenDescriptionIsTooLong() {
        validBrand.setDescription(" ");

        assertThrows(IllegalArgumentDescriptionException.class, () -> brandUseCase.create(validBrand));
    }

    @Test
    void create_ShouldThrowBrandAlreadyExistsException_WhenBrandAlreadyExists() {
        when(brandPersistencePort.existsByName(validBrand.getName())).thenReturn(true);

        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.create(validBrand));
    }
}