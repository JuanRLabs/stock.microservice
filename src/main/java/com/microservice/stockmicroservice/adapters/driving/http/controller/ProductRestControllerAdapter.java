package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.adapters.driving.http.dto.response.ProductResponse;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IProductRequestMapper;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IProductResponseMapper;
import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestControllerAdapter {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    private final IProductResponseMapper productoResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> createProduct(@RequestBody AddProductRequest request) {
        productServicePort.create(productRequestMapper.addRequestToProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list-products")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productServicePort.getAll());
    }

    @GetMapping("/all-products")
    public ResponseEntity<Paginated<ProductResponse>> getAllProducts(int page, int size, String sort, Sorted sorted) {
        PageableRequest pageableRequest = new PageableRequest.Builder()
                .setPage(page)
                .setSize(size)
                .setSort(sort)
                .setSorted(sorted)
                .build();
        return ResponseEntity.ok(productoResponseMapper.toProductResponsePage(productServicePort.listAllProducts(pageableRequest)));
    }
}
