package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservice.stockmicroservice.adapters.driving.http.dto.response.CategoryResponse;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody AddCategoryRequest req){
        categoryServicePort.create(categoryRequestMapper.addRequestToCategory(req));
        return ResponseEntity.status(HttpStatus.CREATED ).build();
    }

    @GetMapping("/")
    public ResponseEntity<Page<CategoryResponse>> listALL(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                          @RequestParam(value = "sort", required = false) String sort
                                                        ){
        return ResponseEntity.ok(categoryResponseMapper
                .toCategoryResponsePage(categoryServicePort.listAllCategories(page, size, sort )));
    }

}
