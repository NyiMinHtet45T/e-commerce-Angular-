package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.output.HomeProductInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.PageInfo;
import org.example.onlineshopbackend.api.input.ProductInfo;
import org.example.onlineshopbackend.model.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<PageInfo<HomeProductInfo>> getAllProducts(@RequestParam(required = false) Long userId,
                                                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "8") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct(userId, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageInfo<HomeProductInfo>> searchProductByName(@RequestParam String name,
                                                                         @RequestParam(required = false) Long userId,
                                                                     @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(required = false, defaultValue = "8") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProductByName(userId,name, page, size));
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<PageInfo<HomeProductInfo>> getProductByCategoryName(@PathVariable("categoryName") String categoryName,
                                                                         @RequestParam(required = false) Long userId,
                                                                         @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                         @RequestParam(required = false, defaultValue = "8") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCategoryName(userId,categoryName, page, size));
    }

    @GetMapping("/add_to_favorite/productId/{productId}/userId/{userId}")
    public ResponseEntity<MessageResponse> addToFavourite(@PathVariable("productId") Long  productId,
                                                          @PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addToFavourite(productId, userId));
    }

    @GetMapping("/productId/{productId}")
    public ResponseEntity<ProductInfo> getProductById(@PathVariable("productId") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<HomeProductInfo>> getAllFavourites(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllFavourites(userId));
    }



}
