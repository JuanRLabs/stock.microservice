package com.microservice.stockmicroservice.domain.spi.product;

import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

import java.util.List;

public interface IProductPersistencePort {
    void create(Product product);
    Product findByName(String name);
    List<Product> getAll();
    Paginated<Product> listAllProducts(PageableRequest pageableRequest);

}
