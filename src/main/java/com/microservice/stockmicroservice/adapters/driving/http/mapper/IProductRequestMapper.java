package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.util.DomainConstants;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "brandMapper")
    @Mapping(source = "categoriesId", target = "categoriesId", qualifiedByName = "CategoryMapper")
    Product addRequestToProduct(AddProductRequest addProductRequest);

    @Named("brandMapper")
    default Brand brandMapper(Long brandId) {
        return new Brand(brandId);
    }

    @Named("CategoryMapper")
    default List<Category> categoryMapper(List<Long> categories) {
        if (!categories.isEmpty()) {
            List<Category> response = new ArrayList<>();
            for (Long id : categories) {
                Category cat = new Category(id, "defaultName", "defDescription");
                response.add(cat);
            }
            return response;
        } throw new EmptyFieldException(DomainConstants.FIELD_CATEGORIESID_ILLEGAL_ARGUMENT_MESSAGE);

    }
}
