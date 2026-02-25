package com.retro.api.repository;

import com.retro.api.entity.CartItem;
import com.retro.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    List<CartItem> findAllByUserId(UUID id);
    Optional<CartItem> findByUserIdAndProductId(UUID userId, UUID productId);
    Optional<CartItem> findByIdAndUserId(UUID itemId, UUID userId);
    void deleteAllByUser(User user);
}
