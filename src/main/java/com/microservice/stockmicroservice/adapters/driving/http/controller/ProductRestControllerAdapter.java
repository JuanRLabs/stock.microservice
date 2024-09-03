package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IProductRequestMapper;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.ProductMapper;
import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestControllerAdapter {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    //private final IProductoResponseMapper productoResponseMapper;
    private final ProductMapper productMapper;

    @PostMapping("/")
    public ResponseEntity<Void> createProduct(@RequestBody AddProductRequest request) {
        productServicePort.create(ProductMapper.fromAddProductRequest(request));
        //productServicePort.create(productMapper.addRequestToProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//
//    @GetMapping("/all-categories")
//    public ResponseEntity<ProductResponse> getAll(int page, int size, String sort, Sorted sorted) {
//        PageableRequest pageableRequest = new PageableRequest.Builder()
//                .setPage(page)
//                .setSize(size)
//                .setSort(sort)
//                .setSorted(sorted)
//                .build();
//        return ResponseEntity.ok(productoResponseMapper.toProductResponsePage(productServicePort.listAllProducts(pageableRequest)));
//    }
}
