package com.microservice.StockMicroservice.infraestructura.in.web;

import com.microservice.StockMicroservice.aplication.port.in.category.CreateCategoryCommon;
import com.microservice.StockMicroservice.aplication.usecase.CreateCategoryServiceImpl;
import com.microservice.StockMicroservice.aplication.usecase.ListCategoryServiceImpl;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import com.microservice.StockMicroservice.domain.exceptions.EmazonExceptions;
import com.microservice.StockMicroservice.domain.enums.APIError;
import org.apache.el.util.Validation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CreateCategoryServiceImpl createCategoryService;
    private final ListCategoryServiceImpl listCategoryService;

    public CategoryController(CreateCategoryServiceImpl createCategoryService,
                              ListCategoryServiceImpl listCategoryService)
    {
        this.createCategoryService = createCategoryService;
        this.listCategoryService = listCategoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDomain> create(@RequestBody CreateCategoryCommon req){
        if (req.getName() == null || req.getDescription() == null ){
            throw new EmazonExceptions(APIError.BAD_FORMAT);
        }
        CategoryDomain response =  createCategoryService.create(req);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED );
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDomain>> listALL(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "sort", required = false) String sort
                                                        ){
        if (page <0 || size < 0 ) throw new EmazonExceptions(APIError.ILLEGAL_PARAMS_REQUEST);
        return new ResponseEntity<>(listCategoryService.ListAll(page, size, sort ), HttpStatus.OK);
    }

}
