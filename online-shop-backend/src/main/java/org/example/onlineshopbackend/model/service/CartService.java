package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.CartItemInfo;
import org.example.onlineshopbackend.api.output.CartInfo;
import org.example.onlineshopbackend.api.output.CartItemResponse;
import org.example.onlineshopbackend.api.output.CartItemTotalAndPrice;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.exception.ApiBusinessException;
import org.example.onlineshopbackend.model.entity.Cart;
import org.example.onlineshopbackend.model.entity.CartItem;
import org.example.onlineshopbackend.model.entity.Product;
import org.example.onlineshopbackend.model.repo.CartItemRepo;
import org.example.onlineshopbackend.model.repo.CartRepo;
import org.example.onlineshopbackend.model.repo.ProductRepo;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;
    private final UserRepo userRepo;

    @Transactional
    public MessageResponse addCartItemToCart(CartItemInfo cartItemInfo) {
        Cart existCart = cartRepo.findCartByUserId(cartItemInfo.userId()).orElse(null);

        Product product = productRepo.findById(cartItemInfo.productId()).orElseThrow(() -> new ApiBusinessException("Product Not Found"));

        if (existCart != null) {
            Optional<CartItem> existCartItem = existCart.getCartItemList().stream()
                    .filter(existItem -> existItem.getProduct().equals(product))
                    .findFirst();
            if (existCartItem.isPresent()) {
                CartItem cartItem = existCartItem.get();
                int totalQuantity = cartItem.getQuantity() + cartItemInfo.quantity();
                addCartQuantity(cartItem.getId(), totalQuantity);
            }else {
                CartItem cartItem = cartItemInfo.toCartItem();
                cartItem.setCart(existCart);
                cartItem.setProduct(product);
                existCart.addCartItem(cartItem);
            }
            cartRepo.save(existCart);
        }else {
            Cart newCart = new Cart();
            newCart.setTotalPrice(0L);
            newCart.setTotalItem(0);
            newCart.setUser(userRepo.findById(cartItemInfo.userId()).orElseThrow(() -> new ApiBusinessException("User Not Found")));

            CartItem newCartItem = cartItemInfo.toCartItem();
            newCartItem.setCart(newCart);
            newCartItem.setProduct(product);
            newCart.addCartItem(newCartItem);
            cartRepo.save(newCart);
        }
        return new MessageResponse("Successfully add to cart");
    }

    public MessageResponse addCartQuantity(Long cartItemId, int totalQuantity) {
        CartItem cartItem = cartItemRepo.findById(cartItemId).orElseThrow(() -> new ApiBusinessException("Cart Item Not Found"));
        cartItem.setQuantity(totalQuantity);
        cartItem.setTotalPrice(cartItem.getProduct().getPrice() * totalQuantity);
        cartItemRepo.save(cartItem);
        return new MessageResponse("Successfully add");
    }

    public CartItemTotalAndPrice cartTotalAndPrice(Long userId) {
        Cart cart = cartRepo.findCartByUserId(userId).orElse(null);

        long totalPrice = 0L;
        int totalItem = 0;

        if (cart != null) {
            for (CartItem cartItem : cart.getCartItemList()) {
                totalPrice += cartItem.getTotalPrice();
                totalItem += cartItem.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            cart.setTotalItem(totalItem);
            cartRepo.save(cart);
            return new CartItemTotalAndPrice(totalPrice, totalItem);
        }
        return null;
    }

    public MessageResponse removeCartItemFromCart(Long cartItemId, Long userId) {
        Cart cart = cartRepo.findCartByUserId(userId).orElseThrow(() -> new ApiBusinessException("Cart Not Found"));
        CartItem cartItem = cartItemRepo.findById(cartItemId).orElseThrow(() -> new ApiBusinessException("Cart Item Not Found"));
        cart.getCartItemList().remove(cartItem);
        cartRepo.save(cart);
        return new MessageResponse("Successfully removed cart from the cart");
    }

    public MessageResponse clearCart(Long userId) {
        Cart cart = cartRepo.findCartByUserId(userId).orElseThrow(() -> new ApiBusinessException("Cart Not Found"));
        cart.getCartItemList().clear();
        cartRepo.save(cart);
        return new MessageResponse("Successfully cleared cart from the cart");
    }

    public CartInfo getCartByUserId(Long userId) {
        return cartRepo.findCartByUserId(userId).map(CartInfo::getCartInfo).orElse(null);
    }

    public CartInfo getCartById(Long cartId) {
        return cartRepo.findById(cartId).map(CartInfo::getCartInfo).orElse(null);
    }


}
