package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.adapter;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final IBrandRepository brandRepository;

    @Override
    public void create(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public Product findByName(String name) {
        return productEntityMapper.toModelOk(productRepository.findByName(name));
    }

    @Override
    public List<Product> getAll() {
        return productEntityMapper.toModelList(productRepository.findAll());
    }

    @Override
    public Paginated<Product> listAllProducts(PageableRequest pageableRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageableRequest.getSorted().name()), pageableRequest.getSort());
        Pageable pageable = PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize(), sort);
        Page<ProductEntity> responseRepository = productRepository.findAll(pageable);
        List<Product> products = productEntityMapper.toModelListPaginated(responseRepository);
        return  new Paginated<>(products, pageableRequest, responseRepository.getTotalPages() ,responseRepository.getTotalElements());
    }


}
