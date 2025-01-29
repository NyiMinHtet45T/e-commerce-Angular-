package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.model.entity.Address;
import org.example.onlineshopbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByName(String username);

    @Query("""
select u from User u join u.products p where p.id =:productId
""")
    Optional<List<User>> findUserByProductId(Long productId);
}
