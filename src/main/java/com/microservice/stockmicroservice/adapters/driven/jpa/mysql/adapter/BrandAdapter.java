package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void create(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }

    @Override
    public boolean existsBrand(Long id) {
        return brandRepository.existsById(id);
    }

    @Override
    public Brand getById(Long id) {
        return brandEntityMapper.toModel(brandRepository.findById(id)) ;
    }

    @Override
    public Paginated<Brand> listAllBrands(PageableRequest pageableRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageableRequest.getSorted().name()), pageableRequest.getSort());
        Pageable pageable = PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize(), sort);
        Page<BrandEntity> responseRepository = brandRepository.findAll(pageable);
        List<Brand> brands = brandEntityMapper.toModelList(responseRepository);
        return new Paginated<Brand>(brands, pageableRequest, responseRepository.getTotalPages(), responseRepository.getTotalElements());
    }
}
