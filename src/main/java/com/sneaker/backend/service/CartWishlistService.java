package com.sneaker.backend.service;

import com.sneaker.backend.dto.CartDto;
import com.sneaker.backend.dto.WishListDto;
import com.sneaker.backend.entities.CartEntity;
import com.sneaker.backend.entities.ProductEntity;
import com.sneaker.backend.entities.UserEntity;
import com.sneaker.backend.entities.WishlistEntity;
import com.sneaker.backend.repository.CartRepo;
import com.sneaker.backend.repository.ProductRepo;
import com.sneaker.backend.repository.WishListRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartWishlistService {


    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private WishListRepo wishListRepo;

    @Autowired
    private ModelMapper mapper;


    // TODO : Optimize the Queries

    public String addToCart(Long productId, Integer size){

        CartEntity cartEntity = cartRepo.findByProductIdAndUserId(productId,getCurrentUserId());
        if (cartEntity != null && cartEntity.getSize().equals(size)) {
            cartEntity.setQuantity(cartEntity.getQuantity() + 1);
            cartRepo.save(cartEntity);
        }
        else{
            ProductEntity product = productRepo.findById(productId).orElseThrow();
            cartRepo.save(CartEntity.builder()
                    .product(product)
                    .quantity(1)
                    .userId(getCurrentUserId())
                    .size(size)
                    .build());
        }
        return "Product added to Cart";
    }

    public List<CartDto> deleteItem(Long productId){
        List<CartEntity> cartEntity = cartRepo.findAllByProductIdAndUserId(productId,getCurrentUserId());
        cartRepo.deleteAll(cartEntity);

        List<CartEntity> cartEntityList = cartRepo.getRestProducts(getCurrentUserId());

        return cartEntityList.stream()
                .map(leftCartItems -> mapper.map(leftCartItems, CartDto.class))
                .toList();
    }

    public List<CartDto> getUserCart(){

        List<CartEntity> cartEntityList = cartRepo.findByUserId(getCurrentUserId());
        return cartEntityList.stream()
                .map(cartEntity -> mapper.map(cartEntity, CartDto.class))
                .toList();
    }

    public CartDto updateQuantity(Long productId, Integer size, Integer quantity) {
        CartEntity cartEntity = cartRepo.findByProductIdAndSizeAndUserId(productId,size,getCurrentUserId());
        if(cartEntity != null){
            cartEntity.setQuantity(quantity);
            cartRepo.save(cartEntity);
        }
        return mapper.map(cartEntity, CartDto.class);
    }

    public String addInWishlist(Long productId){

        if(wishListRepo.findByProductIdAndUserId(productId, getCurrentUserId()) == null){
            ProductEntity product = productRepo.findById(productId).orElseThrow();
            wishListRepo.save(WishlistEntity.builder()
                    .userId(getCurrentUserId())
                    .product(product)
                    .build()
            );
        }
        return "Product added in wishlist";
    }

    public List<WishListDto> deleteFromWishlist(Long productId){
        WishlistEntity wishlistEntity = wishListRepo.findByUserIdAndProductId(getCurrentUserId(),productId);
        wishListRepo.delete(wishlistEntity);

        List<WishlistEntity> wishlist = wishListRepo.getRestItemInWishList(getCurrentUserId());

        return wishlist.stream()
                .map(leftWishlist -> mapper.map(leftWishlist, WishListDto.class))
                .toList();
    }

    public List<WishListDto> getUserWishlist(){
        List<WishlistEntity> list = wishListRepo.findByUserId(getCurrentUserId());

        return list.stream()
                .map(showList-> mapper.map(showList, WishListDto.class))
                .toList();
    }


// To Fetch the current user from security context

    public Long getCurrentUserId() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
