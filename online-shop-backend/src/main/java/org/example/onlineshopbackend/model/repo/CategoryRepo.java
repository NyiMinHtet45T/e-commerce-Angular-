package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.api.output.CategoryInfo;
import org.example.onlineshopbackend.model.entity.Category;

import org.example.onlineshopbackend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByName(String categoryName);
}
