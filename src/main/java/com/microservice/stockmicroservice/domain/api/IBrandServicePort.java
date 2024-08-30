package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Brand;

public interface IBrandServicePort {
    void create(Brand brand);
}
