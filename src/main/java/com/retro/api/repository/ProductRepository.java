package com.retro.api.repository;

import com.retro.api.entity.Product;
import com.retro.api.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByIdAndProductStatus(UUID id, ProductStatus status);

    Optional<Product> findByIdAndProductStatusNot(UUID id, ProductStatus status);

    List<Product> findAllByProductStatus(ProductStatus status);

    boolean existsBySku(String sku);
}
