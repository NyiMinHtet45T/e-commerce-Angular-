package org.example.onlineshopbackend.api.output;

import org.example.onlineshopbackend.api.input.ProductInfo;
import org.example.onlineshopbackend.model.entity.Product;

import java.util.List;

public record HomeProductInfo(
        Long id,
        String name,
        Long price,
        String img,
        boolean available,
        String categoryName,
        boolean isFavourite
) {
    public static HomeProductInfo getHomeProduct(Product product, boolean isFavourite) {
        return new HomeProductInfo(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImg().getFirst(),
                product.isAvailable(),
                product.getCategory().getName(),
                isFavourite);

    }


}
