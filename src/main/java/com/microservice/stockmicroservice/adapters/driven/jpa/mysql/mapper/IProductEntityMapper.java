package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.DataNotFoundToMapper;
import com.microservice.stockmicroservice.configuration.Constants;
import com.microservice.stockmicroservice.domain.exceptions.ProductsNotFoundToMapper;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)// Brand beans
public interface IProductEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brandId", source = "brand")
    ProductEntity toEntity(Product product);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "brandId", target = "brand"),
            @Mapping(source = "categoriesId", target = "categoriesId")
    })
    List<Product> toModelList(List<ProductEntity> productEntities);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "brandId", target = "brand"),
            @Mapping(source = "categoriesId", target = "categoriesId")
    })
    @AfterMapping
    default void afterMapping(ProductEntity productEntity, @MappingTarget Product product) {
        if (productEntity.getCategoriesId() != null) {
            product.setCategoriesId(productEntity.getCategoriesId().stream()
                    .map(categoryEntity -> ICategoryEntityMapper.INSTANCE.toModel(categoryEntity))
                    .collect(Collectors.toList()));
        }
    }

    @Mapping(source = "brandId",target = "brand")
    @Mapping(source = "categoriesId", target = "categoriesId")
    Product toModelOk(ProductEntity productEntity);

    default List<Product> toModelListPaginated(Page<ProductEntity> productEntities){
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            if (productEntity != null) {
                Product converted = toModelOk(productEntity);
                products.add(converted);
            }else {
                throw new DataNotFoundToMapper(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
            }
        }
        return products;
    }
}
