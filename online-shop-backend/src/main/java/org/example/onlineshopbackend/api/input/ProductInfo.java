package org.example.onlineshopbackend.api.input;

import org.example.onlineshopbackend.model.entity.Product;

import java.util.List;

public record ProductInfo(
        Long id,
        String name,
        Long price,
        String description,
        List<String> img,
        boolean available,
        String categoryName
) {

    public static ProductInfo getProductInfo(Product product) {
        return new ProductInfo(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImg(),
                product.isAvailable(),
                product.getCategory().getName());
    }

    public Product infoToProduct() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImg(img);
        product.setAvailable(available);
        return product;
    }
}
