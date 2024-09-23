package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;

public interface IBrandServicePort {
    void create(Brand brand);
    Paginated<Brand> listAllBrands(int page, int size, String sort, Sorted sorted);
}
