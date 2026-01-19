package com.retro.api.repository;

import com.retro.api.entity.Product;
import com.retro.api.entity.ProductState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByIdAndProductState(UUID id, ProductState state);

    Optional<Product> findByIdAndProductStateNot(UUID id, ProductState state);

    List<Product> findAllByProductState(ProductState state);
}
