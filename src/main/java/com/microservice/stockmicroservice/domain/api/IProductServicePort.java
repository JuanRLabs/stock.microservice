package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;

import java.util.List;

public interface IProductServicePort {
    void create(Product product);
    List<Product> getAll();
    Paginated<Product> listAllProducts(int page, int size, String sort, Sorted sorted);

}
