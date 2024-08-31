package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)// Brand beans
public interface IProductEntityMapper {

    @Mapping(source = "categoriesId", target = "", ignore = true)
    ProductEntity toEntity(Product product);
    @Mapping(target = "direccion", constant = "por integrar")
    Product toModel(ProductEntity productEntity);

    Product toModelOk(ProductEntity productEntity);

}
