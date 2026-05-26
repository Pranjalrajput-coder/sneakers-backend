package com.sneaker.backend.service;

import com.sneaker.backend.dto.ProductRequestDto;
import com.sneaker.backend.dto.ProductResponseDto;
import com.sneaker.backend.entities.ProductEntity;
//import com.sneaker.backend.entities.ProductSize;
import com.sneaker.backend.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper mapper;

    public List<ProductResponseDto> getAllProducts(){
        List<ProductEntity> allProducts = productRepo.findAll();
        return allProducts.stream()
                .map(product -> mapper.map(product, ProductResponseDto.class))
                .toList();
    }

    public List<ProductResponseDto> filterByName(String name){
        List<ProductEntity> productByName = productRepo.findByNameContainingIgnoreCase(name);
        return productByName.stream()
                .map(product -> mapper.map(product, ProductResponseDto.class))
                .toList();
    }

    public List<ProductResponseDto> filterByBrand(String brandName){
        List<ProductEntity> productByBrand = productRepo.findByBrand(brandName);
        return productByBrand.stream()
                .map(product -> mapper.map(product,ProductResponseDto.class))
                .toList();
    }

    public List<ProductResponseDto> filterByPriceRange(Double minPrice, Double maxPrice){
        List<ProductEntity> productByPrice = productRepo.findByPriceBetween(minPrice, maxPrice);
        return productByPrice.stream()
                .map(product -> mapper.map(product,ProductResponseDto.class))
                .toList();
    }

    public ProductResponseDto addProduct(ProductRequestDto request){

        ProductEntity productEntity = ProductEntity.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .price(request.getPrice())
                .category(request.getCategory())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .stock(request.getStock())
                .sizes(request.getSizes())
                .build();

        productRepo.save(productEntity);

        return mapper.map(productEntity, ProductResponseDto.class);
    }

}
