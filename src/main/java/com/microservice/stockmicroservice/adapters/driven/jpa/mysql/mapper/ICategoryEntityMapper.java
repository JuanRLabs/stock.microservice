package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
  Category toModel(CategoryEntity categoryEntity);
  CategoryEntity toEntity(Category category);
  default Page<Category> toModelList(Page<CategoryEntity> categoryEntities){
      return categoryEntities.map(this::toModel);
    }
}
