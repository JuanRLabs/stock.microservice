package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryNotFoundToMapper;
import com.microservice.stockmicroservice.configuration.Constants;
import com.microservice.stockmicroservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {

    Brand toModel(Optional<BrandEntity> brandEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    BrandEntity toEntity(Brand brand);

    default List<Brand> toModelList(Page<BrandEntity> brandEntities)
    {
        List<Brand> brands = new ArrayList<>();
        for (BrandEntity brandEntity : brandEntities) {
            if (brandEntity != null) {
                Brand converted = toModel(Optional.of(brandEntity));
                brands.add(converted);
            }else {
                throw new CategoryNotFoundToMapper(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
            }
        }
        return brands;
    }
}
