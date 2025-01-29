package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.output.CategoryInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.model.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
public class AdminCategoryApi {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> createCategory(@RequestBody CategoryInfo categoryInfo, BindingResult result) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryInfo));
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> updateCategory(@RequestBody CategoryInfo categoryInfo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.updateCategory(categoryInfo));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.deleteCategory(categoryId));
    }
}
