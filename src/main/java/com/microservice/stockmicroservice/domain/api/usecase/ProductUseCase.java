package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void create(Product product) {
        Long brandId = product.getBrandId();
        if (productPersistencePort.existsBrand(brandId))
        {
            productPersistencePort.create(product);
        }else
        { throw new IllegalArgumentException("Marca no encontrada");}
    }
}
