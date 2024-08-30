package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddBrandRequest;
import com.microservice.stockmicroservice.adapters.driving.http.dto.response.BrandResponse;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IBrandRequestMapper;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IBrandResponseMapper;
import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> createBrand(@RequestBody AddBrandRequest brandRequest){
        brandServicePort.create(brandRequestMapper.addRequestToModel(brandRequest));
        return ResponseEntity.status(HttpStatus.CREATED ).build();
    }

    @GetMapping("/")
    public ResponseEntity<Paginated<BrandResponse>> listAllBrandsPaginated(int page, int size, String sort, Sorted sorted){
        PageableRequest pageableRequest = new PageableRequest.Builder()
                .setPage(page)
                .setSize(size)
                .setSort(sort)
                .setSorted(sorted)
                .build();
        return ResponseEntity.ok(
                brandResponseMapper.toResponsesPaginated(brandServicePort.listAllBrands(pageableRequest)));
    }

}
