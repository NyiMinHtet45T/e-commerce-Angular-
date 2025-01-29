package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query("""
select o from Order o where o.user.id = :userId
""")
    Optional<List<Order>> findOrderByUserId(Long userId);
}
