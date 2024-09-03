package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;

public interface IProductServicePort {
    void create(Product product);
//    Product listAllProducts(PageableRequest pageableRequest);

}
