package com.microservice.StockMicroservice.aplication.port.out;

import com.microservice.StockMicroservice.domain.CategoryDomain;

import java.util.List;

public interface IListAllCategoryPort {
    List<CategoryDomain> findAll();
}
