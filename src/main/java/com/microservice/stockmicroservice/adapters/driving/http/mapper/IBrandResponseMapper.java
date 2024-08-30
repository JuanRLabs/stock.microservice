package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.response.BrandResponse;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
    default Paginated<BrandResponse> toResponsesPaginated(Paginated<Brand> brands)
   {
        List<BrandResponse> response = new ArrayList<>();
        for (Brand brand : brands.getContent().stream().toList()) {
            if (brand != null) {
                BrandResponse brandResponse = toBrandResponse(brand);
                response.add(brandResponse);
            }
        }
        Paginated<BrandResponse> paginatedResponse = new Paginated<>();
        paginatedResponse.setContent(response);
        paginatedResponse.setTotalElements(brands.getTotalElements());
        paginatedResponse.setTotalPages(brands.getTotalPages());
        return paginatedResponse;
    }
}
