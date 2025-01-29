package org.example.onlineshopbackend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Long totalPrice;

    private OrderStatus orderStatus;

    private UUID trackingId;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public enum OrderStatus {
        Pending,
        Placed,
        Shipped,
        Delivered
    }
}
