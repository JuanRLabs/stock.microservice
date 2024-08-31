package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.ICategoryProductPersistencePort;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;

import java.util.List;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final ICategoryProductPersistencePort categoryProductPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort, ICategoryProductPersistencePort iCategoryProductPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryProductPersistencePort= iCategoryProductPersistencePort;
    }

    @Override
    public void create(Product product) {
        Long brandId = product.getBrandId();
        List<Long> categoriesId = product.getCategoriesId();
        if (productPersistencePort.existsBrand(brandId))
        {
            productPersistencePort.create(product);
            Product dataProduct = productPersistencePort.findByName(product.getName());
            categoryProductPersistencePort.createRelationsCategories(categoriesId, dataProduct.getId());
        }else
        { throw new IllegalArgumentException("Marca no encontrada");}
    }
}
