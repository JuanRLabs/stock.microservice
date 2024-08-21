package com.microservice.StockMicroservice.aplication.port.in.category;

import com.microservice.StockMicroservice.domain.CategoryDomain;

public interface ICreateCategoryPort {

    CategoryDomain create(CreateCategoryCommon req);
}
