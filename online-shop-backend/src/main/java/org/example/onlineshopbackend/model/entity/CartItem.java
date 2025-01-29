package org.example.onlineshopbackend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private long totalPrice;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;


}
