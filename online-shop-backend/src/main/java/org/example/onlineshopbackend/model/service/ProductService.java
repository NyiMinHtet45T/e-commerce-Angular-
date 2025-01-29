package org.example.onlineshopbackend.model.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.output.HomeProductInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.PageInfo;
import org.example.onlineshopbackend.api.input.ProductInfo;
import org.example.onlineshopbackend.exception.ApiBusinessException;
import org.example.onlineshopbackend.model.entity.CartItem;
import org.example.onlineshopbackend.model.entity.Category;
import org.example.onlineshopbackend.model.entity.Product;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.repo.CartItemRepo;
import org.example.onlineshopbackend.model.repo.CategoryRepo;
import org.example.onlineshopbackend.model.repo.ProductRepo;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    private final CartItemRepo cartItemRepo;

    public String createProduct(ProductInfo productInfo) {
        productRepo.save(initProduct(productInfo, true));
        return "Successfully created product";
    }

    public PageInfo<HomeProductInfo> getAllProduct(Long userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepo.getAllProduct(pageable);
        return getHomeProductInfoPageInfo(userId, products);
    }

    private PageInfo<HomeProductInfo> getHomeProductInfoPageInfo(Long userId, Page<Product> products) {
        if (userId == null) {
            return PageInfo.getPageInfo(products.map(pro -> HomeProductInfo.getHomeProduct(pro, false)));
        }
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Product> favouriteProduct = user.getProducts();
        return PageInfo.getPageInfo(products.map(pro -> isThereFavouriteProduct(favouriteProduct, pro)));
    }

    private HomeProductInfo isThereFavouriteProduct(List<Product> favouriteProduct, Product pro) {
        boolean isFavourite = favouriteProduct.contains(pro);
        return HomeProductInfo.getHomeProduct(pro, isFavourite);
    }

    public PageInfo<HomeProductInfo> searchProductByName(Long userId,String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepo.getAllByNameContaining(name, pageable);
        return getHomeProductInfoPageInfo(userId, products);
    }

    public MessageResponse addToFavourite(Long productId, Long userId) {

        Product product = productRepo.findById(productId).orElseThrow(() -> new ApiBusinessException("Product Not Fount"));
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

        if(user.getProducts().contains(product)) {
            user.getProducts().remove(product);
            userRepo.save(user);
            return new MessageResponse("Successfully removed product from Favourites");
        }
        user.addProduct(product);
        userRepo.save(user);
        return new MessageResponse("Successfully added product to Favourites");
    }

    public PageInfo<HomeProductInfo> getProductByCategoryName(Long userId, String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepo.getProductByCategoryName(categoryName, pageable);
        return getHomeProductInfoPageInfo(userId, products);
    }

    public List<HomeProductInfo> getAllFavourites(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
        return user.getProducts().stream().map(product -> HomeProductInfo.getHomeProduct(product, true)).toList();
    }

    public String updateProductAvailable(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ApiBusinessException("Product Not Found"));
        product.setAvailable(!product.isAvailable());
        productRepo.save(product);
        return "Successfully updated product available";
    }

    public String updateProduct(ProductInfo productInfo) {
        if (!productRepo.existsById(productInfo.id())) {
            throw new ApiBusinessException("Product Not Found");
        }
        Product product = initProduct(productInfo, false);
        product.setId(productInfo.id());
        productRepo.save(product);
        return "Successfully updated product";
    }

    public ProductInfo getProductById(Long productId) {
        return ProductInfo.getProductInfo(productRepo.findById(productId).orElseThrow(() -> new ApiBusinessException("Product Not Found")));
    }

    @Transactional
    public String deleteProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ApiBusinessException("Product Not Found"));
        System.out.println(productId);
        userRepo.findUserByProductId(productId).ifPresent(userList -> userList.forEach(user -> {
            user.getProducts().remove(product);
            userRepo.save(user);
        }));
        cartItemRepo.findCartItemByProductId(productId).ifPresent(cartItemList -> cartItemList.stream()
                .filter(item -> Objects.equals(item.getProduct().getId(), productId))
                .forEach(item -> item.setProduct(null)));
        productRepo.delete(product);
        return "Successfully deleted product";
    }

    private Product initProduct(ProductInfo productInfo, Boolean isCreated) {
        Product product = productInfo.infoToProduct();
        Category category = categoryRepo.findCategoryByName(productInfo.categoryName()).orElseThrow(() -> new EntityNotFoundException("Category not found!"));
        if (isCreated) {
            category.addProduct(product);
        }
        product.setCategory(category);
        return product;
    }


}
