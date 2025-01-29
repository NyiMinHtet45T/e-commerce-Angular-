package org.example.onlineshopbackend.api.output;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.model.entity.Order;
import org.example.onlineshopbackend.model.entity.OrderItem;
import org.example.onlineshopbackend.model.entity.OrderItem.OrderStatus;
import org.example.onlineshopbackend.model.entity.Product;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemInfo {

    private Long id;
    private int quantity;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private UUID trackingId;

    private OrderProductInfo orderProductInfo;

    public static OrderItemInfo getOrderItemInfo(OrderItem orderItem, Product product) {
        OrderProductInfo orderProduct = OrderProductInfo.getOrderProductInfo(product);
        return new OrderItemInfo(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getTotalPrice(),
                orderItem.getOrderStatus(),
                orderItem.getTrackingId(),
                orderProduct
        );
    }

}
