package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "category_product")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "product_id")
    private Long productId;

    public CategoryProductEntity(long l, Long productId) {
        this.categoryId= l;
        this.productId=productId;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
