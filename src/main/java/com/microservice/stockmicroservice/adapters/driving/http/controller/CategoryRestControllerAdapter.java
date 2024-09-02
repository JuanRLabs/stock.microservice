package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody AddCategoryRequest req){
        categoryServicePort.create(categoryRequestMapper.addRequestToCategory(req));
        return ResponseEntity.status(HttpStatus.CREATED ).build();
    }
}
