package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.CartItemInfo;
import org.example.onlineshopbackend.api.output.CartInfo;
import org.example.onlineshopbackend.api.output.CartItemTotalAndPrice;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.model.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartApi {

    private final CartService cartService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> addCartItemToCart(@RequestBody CartItemInfo cartItemInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addCartItemToCart(cartItemInfo));
    }

    @GetMapping("/itemId/{cartItemId}/total_quantity/{totalQuantity}")
    public ResponseEntity<MessageResponse> addCartQuantity(@PathVariable("cartItemId") Long cartItemId, @PathVariable("totalQuantity") int totalQuantity) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addCartQuantity(cartItemId, totalQuantity));
    }

    @GetMapping("/total_and_price/{userId}")
    public ResponseEntity<CartItemTotalAndPrice> cartTotalAndPrice(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.cartTotalAndPrice(userId));
    }

    @DeleteMapping("/remove/itemId/{cartItemId}/userId/{userId}")
    public ResponseEntity<MessageResponse> removeCartItemFromCart(@PathVariable("cartItemId") Long cartItemId ,@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.removeCartItemFromCart(cartItemId, userId));
    }

    @DeleteMapping("/clear/userId/{userId}")
    public ResponseEntity<MessageResponse> clearCart(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.clearCart(userId));
    }

    @GetMapping("/get_cart_userId/{userId}")
    public ResponseEntity<CartInfo> getCartByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartByUserId(userId));
    }

    @GetMapping("/get_cart/{cartId}")
    public ResponseEntity<CartInfo> getCartById(@PathVariable("cartId") Long cartId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartById(cartId));
    }
}
