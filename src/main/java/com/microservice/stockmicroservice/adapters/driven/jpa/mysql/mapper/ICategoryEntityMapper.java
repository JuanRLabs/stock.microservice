package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "description", target = "description")
  Category toModel(CategoryEntity categoryEntity);
  CategoryEntity toEntity(Category category);
  default List<Category> toModelList(Page<CategoryEntity> categoryEntities){
    List<Category> categories = new ArrayList<>();
    for (CategoryEntity categoryEntity : categoryEntities) {
      if (categoryEntity != null) {
        Category converted = toModel(categoryEntity);
        categories.add(converted);
      }
    }
    return categories;
  }

}
