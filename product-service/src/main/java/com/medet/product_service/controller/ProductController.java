package com.medet.product_service.controller;

import com.medet.product_service.service.OrderProducer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products = List.of(
            new Product(1L, "Laptop", 1500.0),
            new Product(2L, "Phone", 800.0)
    );

    private final OrderProducer orderProducer;

    public ProductController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        orderProducer.sendOrder(msg);
        return "Mesaj gönderildi: " + msg;
    }

    @GetMapping
    public List<Product> getAll() { return products; }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}

record Product(Long id, String name, Double price) {}