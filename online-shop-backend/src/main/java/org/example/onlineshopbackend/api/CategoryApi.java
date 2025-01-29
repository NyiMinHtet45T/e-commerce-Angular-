package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.output.CategoryInfo;
import org.example.onlineshopbackend.api.output.PageInfo;
import org.example.onlineshopbackend.model.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryInfo>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }
}
