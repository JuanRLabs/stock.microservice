package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

import java.util.List;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void create(Product product) {
        Long brandId = product.getBrand().getId();
        if (brandPersistencePort.existsBrand(brandId))
        {
            productPersistencePort.create(product);
        }else
        { throw new IllegalArgumentException("Marca no encontrada");}
    }

    @Override
    public List<Product> getAll() {
        return productPersistencePort.getAll();
    }


    @Override
    public Paginated<Product> listAllProducts(PageableRequest pageableRequest) {
        return productPersistencePort.listAllProducts(pageableRequest);
    }
}
