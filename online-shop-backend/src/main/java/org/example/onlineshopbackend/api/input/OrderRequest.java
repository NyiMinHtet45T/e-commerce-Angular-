package org.example.onlineshopbackend.api.input;

import org.example.onlineshopbackend.model.entity.Order;

public record OrderRequest(
    Long userId,
    Long addressId
) {
}
