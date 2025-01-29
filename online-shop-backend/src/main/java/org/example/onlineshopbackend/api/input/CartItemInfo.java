package org.example.onlineshopbackend.api.input;

import jakarta.persistence.OneToOne;
import org.example.onlineshopbackend.model.entity.CartItem;
import org.example.onlineshopbackend.model.entity.User;

public record CartItemInfo(
        int quantity,
        long totalPrice,
        Long productId,
        Long userId
) {

    public CartItem toCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(totalPrice);
        return cartItem;
    }


}
