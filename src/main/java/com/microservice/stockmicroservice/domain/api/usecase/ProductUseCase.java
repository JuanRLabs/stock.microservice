package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;

import java.util.List;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void create(Product product) {
        Long brandId = product.getBrandId();
        List<Long> categoriesId = product.getCategoriesId();
        if (!productPersistencePort.existsBrand(brandId)) {
            productPersistencePort.create(product);
            Product productCreated = productPersistencePort.findByName(product.getName());
            Long productId = productCreated.getId();


        }throw new IllegalArgumentException("Marca no encontrada");
    }
}
