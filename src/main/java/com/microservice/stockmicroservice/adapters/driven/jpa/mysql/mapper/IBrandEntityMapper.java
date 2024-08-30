package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryNotFoundToMapper;
import com.microservice.stockmicroservice.configuration.Constants;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    BrandEntity toEntity(Brand brand);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    Brand toModel(BrandEntity brandEntity);

    default List<Brand> toModelList(Page<BrandEntity> brandEntities)
    {
        List<Brand> brands = new ArrayList<>();
        for (BrandEntity brandEntity : brandEntities) {
            if (brandEntity != null) {
                Brand converted = toModel(brandEntity);
                brands.add(converted);
            }else {
                throw new CategoryNotFoundToMapper(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
            }
        }
        return brands;
    }
}
