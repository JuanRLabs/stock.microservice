package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.response.ProductResponse;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring")
public interface IProductResponseMapper {

    Paginated<ProductResponse> toProductResponsePage(Paginated<Product> product);

}
