package com.microservice.stockmicroservice.domain.spi.product;

import com.microservice.stockmicroservice.domain.model.Product;

import java.util.List;

public interface IProductPersistencePort {
    void create(Product product);
    Product findByName(String name);
    List<Product> getAll();

}
