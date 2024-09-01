package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {


    Product addRequestToProduct(AddProductRequest addProductRequest);
}
