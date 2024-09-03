package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.ICategoryProductPersistencePort;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;

import java.util.List;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final ICategoryProductPersistencePort categoryProductPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;
    //añadir la persistencia es lo mejor / si del usecase usare solo una

    public ProductUseCase(IProductPersistencePort productPersistencePort, ICategoryProductPersistencePort iCategoryProductPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryProductPersistencePort= iCategoryProductPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void create(Product product) {
        Long brandId = product.getBrand().getId();
        if (productPersistencePort.existsBrand(brandId))
        {
            List<Long> categoriesId = product.getCategoriesId();
            productPersistencePort.create(product);
            Product dataProduct = productPersistencePort.findByName(product.getName());
            categoryProductPersistencePort.createRelationsCategories(categoriesId, dataProduct.getId());
        }else
        { throw new IllegalArgumentException("Marca no encontrada");}
    }
//
//    @Override
//    public Product listAllProducts(PageableRequest pageableRequest) {
//        return null;
//    }
}
