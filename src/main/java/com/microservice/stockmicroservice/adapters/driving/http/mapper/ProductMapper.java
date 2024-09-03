package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product fromAddProductRequest(AddProductRequest request) {
        Product product = new Product();
        Brand brandInput = new Brand(request.getBrandId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setBrand(brandInput);
        product.setCategories(request.getCategoriesId());
        // Aquí debes obtener las categorías a partir de los ids, te recomiendo crear un método para esto
        // product.setCategories(obtenerCategorias(request.getCategoriesId()));
        return product;
    }

}
