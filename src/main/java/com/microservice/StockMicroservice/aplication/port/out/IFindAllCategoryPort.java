package com.microservice.StockMicroservice.aplication.port.out;

import com.microservice.StockMicroservice.domain.CategoryDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFindAllCategoryPort {
    Page<CategoryDomain> findAllCategories(Pageable pageable);
}
