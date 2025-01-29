package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.api.output.AddressInfo;
import org.example.onlineshopbackend.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Long> {


    @Query("""
select a from Address a where a.user.id = :userId
""")
    List<Address> findByUserId(Long userId);
}
