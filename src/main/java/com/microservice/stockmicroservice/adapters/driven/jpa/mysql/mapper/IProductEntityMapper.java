package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)// Brand beans
public interface IProductEntityMapper {

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(Product product);

    @Mapping(target = "categoriesId", ignore = true )
    Product toModel(ProductEntity productEntity);

    @Mapping(source = "brandId", target = "brandId")
    Product toModelOk(ProductEntity productEntity);


}
