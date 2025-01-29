package org.example.onlineshopbackend.api.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemTotalAndPrice {

    private Long totalPrice;
    private int totalQuantity;

}
