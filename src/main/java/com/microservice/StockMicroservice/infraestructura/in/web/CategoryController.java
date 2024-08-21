package com.microservice.StockMicroservice.infraestructura.in.web;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.aplication.usecase.CreateCategoryServiceImpl;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CreateCategoryServiceImpl createCategoryService;

    public CategoryController(CreateCategoryServiceImpl createCategoryService) {
        this.createCategoryService = createCategoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDomain> create(@RequestBody CreateCategoryCommon req){
        CategoryDomain response =  createCategoryService.create(req);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED );
    }
}
