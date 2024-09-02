package com.microservice.stockmicroservice.domain.spi.Brand;

import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

public interface IBrandPersistencePort {
    void create(Brand brand);
    boolean existsByName(String name);
    Paginated<Brand> listAllBrands(PageableRequest pageableRequest);
}
