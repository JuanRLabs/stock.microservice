package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {

//    @Mapping(target = "id", ignore = true)
    //@Mapping(source = "name", target = "name")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "quantity", target = "quantity")
//    @Mapping(source = "price", target = "price")
//    @Mapping(source = "brandId", target = "brand", resultType = Brand.class)
//    @Mapping(target = "brand.name", constant = "nameBrand")
//    @Mapping(target = "brand.description", constant = "descriptionBrand")
//    @Mapping(source = "categoriesId", target = "categoriesId")
//    Product addRequestToProduct(AddProductRequest addProductRequest);

}
