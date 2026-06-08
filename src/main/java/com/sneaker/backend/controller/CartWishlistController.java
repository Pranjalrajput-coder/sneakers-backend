package com.sneaker.backend.controller;

import com.sneaker.backend.dto.CartDto;
import com.sneaker.backend.dto.WishListDto;
import com.sneaker.backend.entities.WishlistEntity;
import com.sneaker.backend.service.CartWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartWishlistController {

    // TODO : Allow the user to update it's own cart

    @Autowired
    private CartWishlistService cartWishlistService;

    @PostMapping("/cart/add/{productId}/{size}")
    public ResponseEntity<String> addToCart(@PathVariable Long productId,
                                            @PathVariable Integer size){
        try {
            String status = cartWishlistService.addToCart(productId,size);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add to cart");
        }
    }

    @GetMapping("/cart/showCart")
    public ResponseEntity<List<CartDto>> getUserCart(){
        try {
            List<CartDto> cartDto = cartWishlistService.getUserCart();
            return ResponseEntity.ok(cartDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/cart/delete/{productId}")
    public ResponseEntity<List<CartDto>> deleteItem(@PathVariable Long productId){
        try {
            List<CartDto> cartDto = cartWishlistService.deleteItem(productId);
            return ResponseEntity.ok(cartDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/cart/update/{productId}/{size}/{quantity}")
    public ResponseEntity<CartDto> updateQuantity(@PathVariable Long productId, @PathVariable Integer size, @PathVariable Integer quantity){
        try {
            CartDto cartDto = cartWishlistService.updateQuantity(productId, size, quantity);
            return ResponseEntity.ok(cartDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/wishlist/add/{productId}")
    public ResponseEntity<String> addInWishlist(@PathVariable Long productId){
        try {
            return ResponseEntity.ok(cartWishlistService.addInWishlist(productId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Product already in wishlist");
        }
    }

    @GetMapping("/wishlist/showList")
    public ResponseEntity<List<WishListDto>> showList(){
        try {
            return ResponseEntity.ok(cartWishlistService.getUserWishlist());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/wishlist/delete/{productId}")
    public ResponseEntity<List<WishListDto>> deleteFromWishlist(@PathVariable Long productId){
        try {
            List<WishListDto> wishlist = cartWishlistService.deleteFromWishlist(productId);
            return ResponseEntity.ok(wishlist);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
