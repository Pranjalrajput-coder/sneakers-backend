package com.sneaker.backend.utils;

import com.sneaker.backend.entities.ProductEntity;
import com.sneaker.backend.repository.ProductRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private ProductRepo productRepository;

    @PostConstruct
    public void seedData() {

        // only seed if empty
        if (productRepository.count() > 0) return;

        List<ProductEntity> products = List.of(
                createProduct("Air Jordan 1 Retro High", "Nike", 12999.0,
                        "Classic basketball sneaker", "https://i.imgur.com/jordan1.jpg",
                        50, "BASKETBALL", List.of("7", "8", "9", "10", "11")),

                createProduct("Adidas Superstar", "Adidas", 8999.0,
                        "Iconic shell toe sneaker", "https://i.imgur.com/superstar.jpg",
                        30, "LIFESTYLE", List.of("6", "7", "8", "9", "10")),

                createProduct("Nike Air Force 1", "Nike", 9999.0,
                        "Timeless classic", "https://i.imgur.com/af1.jpg",
                        40, "LIFESTYLE", List.of("7", "8", "9", "10")),

                createProduct("New Balance 550", "New Balance", 7999.0,
                        "Retro basketball style", "https://i.imgur.com/nb550.jpg",
                        25, "LIFESTYLE", List.of("7", "8", "9", "10", "11")),

                createProduct("Yeezy Boost 350", "Adidas", 19999.0,
                        "Premium lifestyle sneaker", "https://i.imgur.com/yeezy.jpg",
                        15, "LIFESTYLE", List.of("7", "8", "9", "10")),

                createProduct("Nike Dunk Low", "Nike", 8999.0,
                        "Skate inspired design", "https://i.imgur.com/dunklow.jpg",
                        35, "SKATE", List.of("6", "7", "8", "9", "10", "11")),

                createProduct("Adidas Stan Smith", "Adidas", 6999.0,
                        "Clean minimal tennis shoe", "https://i.imgur.com/stansmith.jpg",
                        45, "TENNIS", List.of("6", "7", "8", "9", "10")),

                createProduct("Puma RS-X", "Puma", 7499.0,
                        "Chunky retro runner", "https://i.imgur.com/pumarsx.jpg",
                        20, "RUNNING", List.of("7", "8", "9", "10"))
        );

        productRepository.saveAll(products);
        System.out.println("✅ Sample products seeded successfully!");
    }

    private ProductEntity createProduct(String name, String brand, Double price,
                                  String desc, String imageUrl,
                                  Integer stock, String category,
                                  List<String> sizes) {
        ProductEntity p = new ProductEntity();
        p.setName(name);
        p.setBrand(brand);
        p.setPrice(price);
        p.setDescription(desc);
        p.setImageUrl(imageUrl);
        p.setStock(stock);
        p.setCategory(category);
        p.setSizes(sizes);
        return p;
    }
}
