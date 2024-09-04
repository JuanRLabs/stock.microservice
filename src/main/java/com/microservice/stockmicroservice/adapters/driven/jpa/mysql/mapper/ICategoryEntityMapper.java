package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryNotFoundToMapper;
import com.microservice.stockmicroservice.adapters.driving.http.controller.ProductsNotFoundToMapper;
import com.microservice.stockmicroservice.configuration.Constants;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)// Brand beans
public interface ICategoryEntityMapper {

  ICategoryEntityMapper INSTANCE = Mappers.getMapper(ICategoryEntityMapper.class);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  Category toModel(CategoryEntity categoryEntity);

  CategoryEntity toEntity(Category category);

  default List<Category> toModelList(Page<CategoryEntity> categoryEntities){
    List<Category> categories = new ArrayList<>();
    for (CategoryEntity categoryEntity : categoryEntities) {
      if (categoryEntity != null) {
        Category converted = toModel(categoryEntity);
        categories.add(converted);
      }else {
        throw new ProductsNotFoundToMapper(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
      }
    }
    return categories;
  }

}
