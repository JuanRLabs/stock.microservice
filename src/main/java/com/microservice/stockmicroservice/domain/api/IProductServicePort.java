package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Product;

public interface IProductServicePort {
    void create(Product product);
}
