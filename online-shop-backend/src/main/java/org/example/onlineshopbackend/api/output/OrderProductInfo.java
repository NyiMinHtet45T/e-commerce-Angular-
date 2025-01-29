package org.example.onlineshopbackend.api.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.model.entity.Product;

@Getter
@Setter
@AllArgsConstructor
public class OrderProductInfo {

    private Long id;
    private String img;
    private String name;
    private Long price;

    public static OrderProductInfo getOrderProductInfo(Product product) {
        return new OrderProductInfo(product.getId(), product.getImg().getFirst(), product.getName(), product.getPrice());
    }
}
