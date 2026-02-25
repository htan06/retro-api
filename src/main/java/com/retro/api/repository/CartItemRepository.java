package com.retro.api.repository;

import com.retro.api.entity.CartItem;
import com.retro.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> findByProductId(UUID productId);
    Optional<CartItem> findByIdAndUserId(UUID itemId, UUID userId);
    void deleteAllByUser(User user);
}
