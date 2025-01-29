package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p ")
    Page<Product> getAllProduct(Pageable pageable);

    @Query("""
select p from Product p where lower(p.name) like lower(concat('%', :name, '%'))
""")
    Page<Product> getAllByNameContaining(String name, Pageable pageable);

    @Query("""
select p from Product p where p.category.name = :categoryName
""")
    Page<Product> getProductByCategoryName(String categoryName, Pageable pageable);
}
