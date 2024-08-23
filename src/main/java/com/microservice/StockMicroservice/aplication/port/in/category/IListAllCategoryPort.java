package com.microservice.StockMicroservice.aplication.port.in.category;

import com.microservice.StockMicroservice.domain.CategoryDomain;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IListAllCategoryPort {
    Page<CategoryDomain> ListAll(int page, int size, String sort);
}
