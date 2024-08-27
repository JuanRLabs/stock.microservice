package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);

}
