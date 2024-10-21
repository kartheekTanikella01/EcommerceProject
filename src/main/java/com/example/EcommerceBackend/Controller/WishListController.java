package com.example.EcommerceBackend.Controller;

import com.example.EcommerceBackend.DTO.WishlistDto;
import com.example.EcommerceBackend.Service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    // Add products to wishlist
    @PostMapping("/add/{userId}")
    public ResponseEntity<WishlistDto> addProductToWishlist(@RequestBody WishlistDto wishlistDto, @PathVariable int userId) {
        WishlistDto updatedWishlist = wishListService.addProductToWishlist(wishlistDto, userId);
        return ResponseEntity.ok(updatedWishlist);
    }

    // Move products to cart
    @PostMapping("/move-to-cart/{userId}")
    public ResponseEntity<Void> moveProductsToCart(@PathVariable int userId) {
        wishListService.moveProductsToCart(userId);
        return ResponseEntity.ok().build();
    }

    // Remove product from wishlist
    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<WishlistDto> removeProductFromWishlist(@PathVariable Integer userId, @PathVariable Integer productId) {
        try {
            WishlistDto updatedWishlist = wishListService.removeProductFromWishlist(userId, productId);
            return ResponseEntity.ok(updatedWishlist);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
