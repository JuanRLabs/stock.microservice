package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.response.CategoryResponse;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);


    default Page<CategoryResponse> toCategoryResponsePage(Page<Category> categories){
        return categories.map(this::toCategoryResponse);
    }
}
