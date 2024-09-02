package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.response.CategoryResponse;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

}
