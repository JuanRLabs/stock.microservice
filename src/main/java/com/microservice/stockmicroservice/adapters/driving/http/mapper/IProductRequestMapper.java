package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.model.Product;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "brandMapper")
    @Mapping(source = "categoriesId", target = "categoriesId", qualifiedByName = "CategoryMapper")
    Product addRequestToProduct(AddProductRequest addProductRequest);

    @Named("brandMapper")
    default Brand brandMapper(Long brandId) {
        Brand brand = new Brand(brandId);
        return brand;
    }

    @Named("CategoryMapper")
    default List<Category> brandMapper(List<Long> categories) {
        List<Category> response = new ArrayList<>();
        for (Long id : categories) {
            Category cat = new Category(id, "default", "def");
            response.add(cat);
        }
        return response;
    }

}
