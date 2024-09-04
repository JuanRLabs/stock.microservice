package com.microservice.stockmicroservice.adapters.driving.http.mapper;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        //product.setCategories(request.getCategoriesId());
        // Aquí debes obtener las categorías a partir de los ids, te recomiendo crear un método para esto
        product.setCategories(obtenerCategorias(request.getCategoriesId()));
        return product;
    }

    private static List<Category> obtenerCategorias(List<Long> categoriesId) {
            List<Category> categories = new ArrayList<>();
            for (Long categoryId : categoriesId) {
                Category category = obtenerCategoria(categoryId);
                categories.add(category);
            }
            return categories;
    }


    public static Category obtenerCategoria(Long categoryId) {
        // Aquí debes implementar la lógica para obtener la categoría a partir del id
        // Puedes usar un repositorio o una base de datos
        // Por ejemplo:
         return new Category(categoryId, "default", "default");
    }
}
