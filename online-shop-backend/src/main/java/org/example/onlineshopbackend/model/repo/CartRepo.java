package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query("""
select c from Cart c where c.user.id = :userId
""")
    Optional<Cart> findCartByUserId(Long userId);
}
