package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("""
select ci from CartItem ci where ci.product.id =:productId
""")
    Optional<List<CartItem>> findCartItemByProductId(Long productId);
}
