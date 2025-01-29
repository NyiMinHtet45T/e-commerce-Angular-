package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.ProductInfo;
import org.example.onlineshopbackend.model.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
public class AdminProductApi {

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<String> createProduct(@RequestBody ProductInfo productInfo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productInfo));
    }

    @GetMapping("/update/available/{productId}")
    public ResponseEntity<String> updateProductAvailable(@PathVariable("productId") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductAvailable(productId));
    }

    @PutMapping("/")
    public ResponseEntity<String> updateProduct(@RequestBody ProductInfo productInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productInfo));
    }

    @DeleteMapping("/{productId}/userId/{userId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
    }
}
