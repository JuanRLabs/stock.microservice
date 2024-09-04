package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

import java.util.List;

public interface IProductServicePort {
    void create(Product product);
    List<Product> getAll();
    Paginated<Product> listAllProducts(PageableRequest pageableRequest);

}
