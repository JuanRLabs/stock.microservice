package com.microservice.stockmicroservice.domain.spi.product;

import com.microservice.stockmicroservice.domain.model.Product;

public interface IProductPersistencePort {
    void create(Product product);
}
