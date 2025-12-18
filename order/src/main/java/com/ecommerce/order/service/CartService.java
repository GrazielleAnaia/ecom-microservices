package com.ecommerce.order.service;


import com.ecommerce.order.clients.ProductServiceClient;
import com.ecommerce.order.clients.UserServiceClient;
import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class CartService {

    private final CartItemRepository cartItemRepository;

    private final ProductServiceClient productServiceClient;

    private final UserServiceClient userServiceClient;


    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {

        //Validation in MS will take place with inter service communication
        ProductResponse productResponse = productServiceClient.getProductDetails(cartItemRequest.getProductId());
        if (productResponse == null || productResponse.getStockQuantity() < cartItemRequest.getQuantity())
            return false;

        UserResponse userResponse = userServiceClient.getUserDetails(userId);
        if (userResponse == null)
            return false;

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, cartItemRequest.getProductId());

        if (existingCartItem != null) {
            //Update quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
//            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        } else {
            //Create a new cart
            CartItem cartItem = new CartItem();
            cartItem.setProductId(cartItemRequest.getProductId());
            cartItem.setUserId(userId);
            cartItem.setQuantity(cartItemRequest.getQuantity());
//            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
