package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "categoriesId", source = "categoriesId")
    Product addRequestToProduct(AddProductRequest addProductRequest);
}
