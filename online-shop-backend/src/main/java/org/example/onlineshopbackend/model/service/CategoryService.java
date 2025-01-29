package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.output.CategoryInfo;
import org.example.onlineshopbackend.api.output.HomeProductInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.PageInfo;
import org.example.onlineshopbackend.exception.ApiBusinessException;
import org.example.onlineshopbackend.model.entity.Category;
import org.example.onlineshopbackend.model.entity.Product;
import org.example.onlineshopbackend.model.repo.CategoryRepo;
import org.example.onlineshopbackend.model.repo.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public MessageResponse createCategory(CategoryInfo categoryInfo) {
        categoryRepo.save(categoryInfo.infoToCategory());
        return new MessageResponse("Successfully created category");
    }

    public List<CategoryInfo> getAllCategories() {
        return categoryRepo.findAll().stream().map(CategoryInfo::categoryToInfo).toList();
    }

    public MessageResponse updateCategory(CategoryInfo categoryInfo) {
        Category category = categoryInfo.infoToCategory();
        category.setId(categoryInfo.id());
        categoryRepo.save(category);
        return new MessageResponse("Category updated successfully");
    }

    public MessageResponse deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ApiBusinessException("Category not found"));
        categoryRepo.delete(category);
        return new MessageResponse("Category deleted successfully");
    }
}
