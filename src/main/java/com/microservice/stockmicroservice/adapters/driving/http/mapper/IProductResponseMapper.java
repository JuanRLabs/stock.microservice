package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.response.ProductResponse;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductResponseMapper {

    Paginated<ProductResponse> toProductResponsePage(Paginated<Product> product);

}
