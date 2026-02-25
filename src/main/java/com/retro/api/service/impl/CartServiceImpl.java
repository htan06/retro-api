package com.retro.api.service.impl;

import com.retro.api.entity.CartItem;
import com.retro.api.entity.User;
import com.retro.api.exception.CatalogException;
import com.retro.api.exception.CatalogExceptionEnum;
import com.retro.api.repository.CartItemRepository;
import com.retro.api.repository.ProductRepository;
import com.retro.api.repository.UserRepository;
import com.retro.api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CartItem addToCart(User user, UUID productId) {
        try {
            CartItem item = cartItemRepository.findByProductId(productId)
                    .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));

            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item);
            return item;
        } catch (Exception e) {
            CartItem item = CartItem.builder()
                    .user(user)
                    .product(productRepository.getReferenceById(productId))
                    .build();

            user.getCartItems().add(item);
            userRepository.save(user);
            return item;
        }
    }

    @Override
    public CartItem increaseQuantity(User user, UUID itemId, int quantity) {
        CartItem item = cartItemRepository.findByIdAndUserId(itemId, user.getId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));

        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
        return item;
    }

    @Override
    public CartItem decreaseQuantity(User user, UUID itemId, int quantity) {
        CartItem item = cartItemRepository.findByIdAndUserId(itemId, user.getId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));

        item.setQuantity(item.getQuantity() - 1);
        cartItemRepository.save(item);
        return item;
    }

    @Override
    public void removeItem(User user, UUID itemId) {
        CartItem item = cartItemRepository.findByIdAndUserId(itemId, user.getId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CART_ITEM_NOT_FOUD));
        cartItemRepository.delete(item);
    }

    @Override
    public void removeAllItem(User user) {
        cartItemRepository.deleteAllByUser(user);
    }
}
