package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)// Brand beans
public interface IProductEntityMapper {

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(Product product);

    default List<Long> getDefaultCategoriesId() {
        return List.of(1L);
    }
    @Mapping(target = "categoriesId", expression = "java( getDefaultCategoriesId() )")
    Product toModelCreated(ProductEntity productEntity);

    @Mapping(source = "brandId", target = "brandId")
    Product toModelOk(ProductEntity productEntity);


}
