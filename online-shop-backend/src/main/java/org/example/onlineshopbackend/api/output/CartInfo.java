package org.example.onlineshopbackend.api.output;

import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.api.input.CartItemInfo;
import org.example.onlineshopbackend.model.entity.Cart;

import java.util.List;

@Getter
@Setter
public class CartInfo {

    private Long id;
    private Long totalPrice;
    private int totalItem;

    private List<CartItemResponse> cartItemResponses;

    public static CartInfo getCartInfo(Cart cart) {
        CartInfo cartInfo = new CartInfo();
        cartInfo.setId(cart.getId());
        cartInfo.setTotalPrice(cart.getTotalPrice());
        cartInfo.setTotalItem(cart.getTotalItem());
        cartInfo.setCartItemResponses(
                CartItemResponse.getCartItemResponse(cart.getCartItemList())
        );
        return cartInfo;
    }

}
