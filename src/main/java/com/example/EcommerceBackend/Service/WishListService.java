package com.example.EcommerceBackend.Service;

import com.example.EcommerceBackend.DTO.ProductDto;
import com.example.EcommerceBackend.DTO.WishlistDto;
import com.example.EcommerceBackend.Entity.Cart;
import com.example.EcommerceBackend.Entity.Product;
import com.example.EcommerceBackend.Entity.User;
import com.example.EcommerceBackend.Entity.WishList;
import com.example.EcommerceBackend.Repository.CartRepository;
import com.example.EcommerceBackend.Repository.ProductRepository;
import com.example.EcommerceBackend.Repository.UserRepo;
import com.example.EcommerceBackend.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    // Add product to user's wishlist
    public WishlistDto addProductToWishlist(WishlistDto wishlistDto, int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WishList wishList = user.getWishList();
        if (wishList == null) {
            wishList = new WishList();
            wishList.setUser(user);
        }

        List<Product> products = productRepository.findAllById(
                wishlistDto.getProductDtos().stream().map(ProductDto::getId).collect(Collectors.toList())
        );

        wishList.getProducts().addAll(products);
        wishlistRepository.save(wishList);

        return convertWishListToDto(wishList);
    }

    // Move products from wishlist to cart
    public void moveProductsToCart(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WishList wishList = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        cart.getProducts().addAll(wishList.getProducts());
        cartRepository.save(cart);

        wishList.getProducts().clear();
        wishlistRepository.save(wishList);
    }

    // Remove product from wishlist
    public WishlistDto removeProductFromWishlist(Integer userId, Integer productId) throws Exception {
        WishList wishList = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception("Wishlist not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));

        boolean removed = wishList.getProducts().removeIf(p -> p.getId().equals(productId));
        if (!removed) {
            throw new Exception("Product not present in wishlist");
        }

        wishlistRepository.save(wishList);
        return convertWishListToDto(wishList);
    }

    private WishlistDto convertWishListToDto(WishList wishList) {
        WishlistDto wishlistDto = new WishlistDto();
        wishlistDto.setId(wishList.getId());
        wishlistDto.setProductDtos(
                wishList.getProducts().stream().map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDescription()
                )).collect(Collectors.toList())
        );
        return wishlistDto;
    }
}
