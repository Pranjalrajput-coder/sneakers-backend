package com.sneaker.backend.utils;

import com.sneaker.backend.entities.ProductEntity;
import com.sneaker.backend.repository.ProductRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DataSeeder {

    @Autowired
    private ProductRepo productRepository;

    @PostConstruct
    public void seedData() {

        // only seed if empty
        if (productRepository.count() > 0) return;

        List<ProductEntity> products = List.of(

                createProduct(
                        "Air Jordan 1 Retro High",
                        "Nike",
                        12999.0,
                        "Classic basketball sneaker",
                        "https://i.imgur.com/jordan1.jpg",
                        "BASKETBALL",
                        Map.of(
                                "7", 10,
                                "8", 12,
                                "9", 15,
                                "10", 8,
                                "11", 5
                        )
                ),

                createProduct(
                        "Adidas Superstar",
                        "Adidas",
                        8999.0,
                        "Iconic shell toe sneaker",
                        "https://i.imgur.com/superstar.jpg",
                        "LIFESTYLE",
                        Map.of(
                                "6", 8,
                                "7", 12,
                                "8", 15,
                                "9", 10,
                                "10", 6
                        )
                ),

                createProduct(
                        "Nike Air Force 1",
                        "Nike",
                        9999.0,
                        "Timeless classic",
                        "https://i.imgur.com/af1.jpg",
                        "LIFESTYLE",
                        Map.of(
                                "7", 10,
                                "8", 15,
                                "9", 8,
                                "10", 7
                        )
                ),

                createProduct(
                        "New Balance 550",
                        "New Balance",
                        7999.0,
                        "Retro basketball style",
                        "https://i.imgur.com/nb550.jpg",
                        "LIFESTYLE",
                        Map.of(
                                "7", 5,
                                "8", 10,
                                "9", 12,
                                "10", 8,
                                "11", 6
                        )
                ),

                createProduct(
                        "Yeezy Boost 350",
                        "Adidas",
                        19999.0,
                        "Premium lifestyle sneaker",
                        "https://i.imgur.com/yeezy.jpg",
                        "LIFESTYLE",
                        Map.of(
                                "7", 4,
                                "8", 6,
                                "9", 8,
                                "10", 5
                        )
                ),

                createProduct(
                        "Nike Dunk Low",
                        "Nike",
                        8999.0,
                        "Skate inspired design",
                        "https://i.imgur.com/dunklow.jpg",
                        "SKATE",
                        Map.of(
                                "6", 5,
                                "7", 8,
                                "8", 10,
                                "9", 12,
                                "10", 7,
                                "11", 4
                        )
                ),

                createProduct(
                        "Adidas Stan Smith",
                        "Adidas",
                        6999.0,
                        "Clean minimal tennis shoe",
                        "https://i.imgur.com/stansmith.jpg",
                        "TENNIS",
                        Map.of(
                                "6", 10,
                                "7", 15,
                                "8", 20,
                                "9", 10,
                                "10", 5
                        )
                ),

                createProduct(
                        "Puma RS-X",
                        "Puma",
                        7499.0,
                        "Chunky retro runner",
                        "https://i.imgur.com/pumarsx.jpg",
                        "RUNNING",
                        Map.of(
                                "7", 7,
                                "8", 10,
                                "9", 12,
                                "10", 6
                        )
                )
        );

        productRepository.saveAll(products);
        System.out.println("✅ Sample products seeded successfully!");
    }

    private ProductEntity createProduct(String name, String brand, Double price,
                                        String desc, String imageUrl,
                                        String category,
                                        Map<String, Integer> sizeStock) {
        ProductEntity p = new ProductEntity();
        p.setName(name);
        p.setBrand(brand);
        p.setPrice(price);
        p.setDescription(desc);
        p.setImageUrl(imageUrl);
        p.setCategory(category);
        p.setSizeStock(sizeStock);
        return p;
    }
}
