package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.adapters.driving.http.dto.response.CategoryResponse;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);
    default Paginated<CategoryResponse> toCategoryResponsePage(Paginated<Category> categories){
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories.getContent().stream().toList()) {
            if (category != null) {
                CategoryResponse categoryResponse = toCategoryResponse(category);
                categoryResponses.add(categoryResponse);
            }
        }
        Paginated<CategoryResponse> paginatedResponse = new Paginated<>();
        paginatedResponse.setContent(categoryResponses);
        paginatedResponse.setTotalElements(categories.getTotalElements());
        paginatedResponse.setTotalPages(categories.getTotalPages());
        paginatedResponse.setPageableRequest(categories.getPageableRequest());
        return paginatedResponse;
    }
}
