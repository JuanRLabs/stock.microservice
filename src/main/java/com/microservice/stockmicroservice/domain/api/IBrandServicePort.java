package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

public interface IBrandServicePort {
    void create(Brand brand);
    Paginated<Brand> listAllBrands(PageableRequest pageableRequest);
}
