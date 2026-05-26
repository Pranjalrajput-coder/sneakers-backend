package com.sneaker.backend.controller;

import com.sneaker.backend.dto.ProductRequestDto;
import com.sneaker.backend.dto.ProductResponseDto;
import com.sneaker.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> responseDto = productService.getAllProducts();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> getProductByName(@RequestParam String name){
        try {
            List<ProductResponseDto> responseDto = productService.filterByName(name);
            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e){
         return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<ProductResponseDto>> getProductByBrand(@PathVariable String brandName){
        try {
            List<ProductResponseDto> responseDto = productService.filterByBrand(brandName);
            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponseDto>> filterByPrice(@RequestParam(required = false) Double minPrice,
                                                            @RequestParam(required = false) Double maxPrice){
        try {
            List<ProductResponseDto> responseDto = productService.filterByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/createProduct")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto responseDto = productService.addProduct(productRequestDto);
            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

//    @DeleteMapping("/removeProduct/{id}")
//    public ResponseEntity<ProductResponseDto> deleteProduct(@RequestBody Long id){
//        try {
//            ProductResponseDto responseDto = productService.deleteProduct(id);
//            return ResponseEntity.ok(responseDto);
//        }
//        catch (Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
