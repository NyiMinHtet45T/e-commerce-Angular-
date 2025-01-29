package org.example.onlineshopbackend.api.output;

import org.example.onlineshopbackend.model.entity.CartItem;
import org.example.onlineshopbackend.model.entity.Product;

import java.util.List;

public record CartItemResponse(
         Long id,
         int quantity,
         long totalPrice,

         Long productId,
         String productImg,
         String productName,
         Long productPrice
) {

    public static List<CartItemResponse> getCartItemResponse(List<CartItem> cartItems) {
        return cartItems.stream().map(item -> new CartItemResponse(
                item.getId(),
                item.getQuantity(),
                item.getTotalPrice(),
                item.getProduct().getId(),
                item.getProduct().getImg().getFirst(),
                item.getProduct().getName(),
                item.getProduct().getPrice()
        )).toList();
    }

}
