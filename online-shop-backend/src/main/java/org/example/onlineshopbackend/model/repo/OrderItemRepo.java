package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
