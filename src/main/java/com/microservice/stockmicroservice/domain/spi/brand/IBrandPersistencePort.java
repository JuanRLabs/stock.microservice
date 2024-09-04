package com.microservice.stockmicroservice.domain.spi.brand;

import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

public interface IBrandPersistencePort {
    void create(Brand brand);
    boolean existsByName(String name);
    boolean existsBrand(Long id);
    Brand getById(Long id);
    Paginated<Brand> listAllBrands(PageableRequest pageableRequest);
}
