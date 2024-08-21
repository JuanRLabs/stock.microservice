package com.microservice.StockMicroservice.aplication.port.out;

import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.infraestructura.out.persistence.CategoryEntity;

public interface ISaveCategoryPort  {

    CategoryEntity save(CategoryDomain category);

}
