package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalBrandException;
import com.microservice.stockmicroservice.domain.exceptions.ProductAlreadyExistsException;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.model.Product;
import com.microservice.stockmicroservice.domain.spi.brand.IBrandPersistencePort;
import com.microservice.stockmicroservice.domain.spi.product.IProductPersistencePort;
import com.microservice.stockmicroservice.domain.util.DomainConstants;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;

import java.util.List;
import java.util.Objects;

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
        if (brandPersistencePort.existsBrand(brandId)){

            if(Objects.isNull(productPersistencePort.findByName(product.getName().trim()))){

              if (product.getCategoriesId().stream().map(Category::getId).distinct().count() != product.getCategoriesId().size() ) {
                throw new EmptyFieldException(DomainConstants.FIELD_CATEGORIESID_CONTAIN_ILLEGAL_ARGUMENT_MESSAGE);
                }
                productPersistencePort.create(product);
            }
            throw new ProductAlreadyExistsException();
            }
            throw new IllegalBrandException();
    }

    @Override
    public List<Product> getAll() {
        return productPersistencePort.getAll();
    }


    @Override
    public Paginated<Product> listAllProducts(int page, int size, String sort, Sorted sorted) {
        String sortType = sort;
        switch (sortType){
            case "name" : {
                    break;
            }
            case "brandName" :{
                sort = "brandId.name";
                break;
            }
            case "categoryName": {
                sort = "categoriesId.name";
            }
        }
        PageableRequest pageableRequest = new PageableRequest.Builder()
                .setPage(page)
                .setSize(size)
                .setSort(sort)
                .setSorted(sorted)
                .build();

        return productPersistencePort.listAllProducts(pageableRequest);
    }
}
