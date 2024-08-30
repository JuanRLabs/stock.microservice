package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservice.stockmicroservice.adapters.driving.http.dto.response.CategoryResponse;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;
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
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody AddCategoryRequest req){
        categoryServicePort.create(categoryRequestMapper.addRequestToCategory(req));
        return ResponseEntity.status(HttpStatus.CREATED ).build();
    }

    @GetMapping("/")
    public ResponseEntity<Paginated<CategoryResponse>> listALL(int page, int size, String sort, Sorted sorted){
        PageableRequest pageableRequest = new PageableRequest.Builder()
                .setPage(page)
                .setSize(size)
                .setSort(sort)
                .setSorted(sorted)
                .build();
        return ResponseEntity.ok(categoryResponseMapper
                .toCategoryResponsePage(categoryServicePort.listAllCategories(pageableRequest)));
    }

}
