package com.retro.api.service.impl;

import com.retro.api.dto.cart.respoonse.CartItemResponse;
import com.retro.api.entity.CartItem;
import com.retro.api.entity.User;
import com.retro.api.exception.CatalogException;
import com.retro.api.exception.CatalogExceptionEnum;
import com.retro.api.repository.CartItemRepository;
import com.retro.api.repository.ProductRepository;
import com.retro.api.repository.UserRepository;
import com.retro.api.service.CartService;
import com.retro.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<CartItemResponse> getCart(UUID userId) {
        return cartItemRepository.findAllByUserId(userId)
                .stream()
                .map(CartItemResponse::from)
                .toList();
    }

    @Override
    public CartItemResponse addToCart(UUID userId, UUID productId) {
        try {
            CartItem item = cartItemRepository.findByUserIdAndProductId(userId, productId)
                    .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));

            item.setQuantity(item.getQuantity() + 1);
            return CartItemResponse.from(
                cartItemRepository.save(item));

        } catch (Exception e) {
            CartItem item = CartItem.builder()
                    .user(userRepository.getReferenceById(userId))
                    .product(productRepository.getReferenceById(productId))
                    .quantity(1)
                    .build();

            return CartItemResponse.from(
                    cartItemRepository.save(item));
        }
    }

    @Override
    public CartItemResponse increaseQuantity(UUID userId, UUID itemId, int quantity) {
        CartItem item = cartItemRepository.findByIdAndUserId(itemId, userId)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));

        item.setQuantity(item.getQuantity() + 1);
        return CartItemResponse.from(
                cartItemRepository.save(item));
    }

    @Override
    public CartItemResponse decreaseQuantity(UUID userId, UUID itemId, int quantity) {
        CartItem item = cartItemRepository.findByIdAndUserId(itemId, userId)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));

        item.setQuantity(item.getQuantity() - 1);
        return CartItemResponse.from(
                cartItemRepository.save(item));
    }

    @Override
    public void removeItem(UUID userId, UUID itemId) {
        CartItem item = cartItemRepository.findByIdAndUserId(itemId, userId)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));
        cartItemRepository.delete(item);
    }

    @Override
    @Transactional
    public void removeAllItem(UUID userId) {
        cartItemRepository.deleteAllByUser(userRepository.getReferenceById(userId));
    }
}
