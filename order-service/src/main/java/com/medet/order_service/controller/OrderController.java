package com.medet.order_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final List<Order> orders = new ArrayList<>();
    private final WebClient.Builder webClientBuilder;

    public OrderController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping
    public List<Order> getAll() {
        return orders;
    }

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return webClientBuilder.build()
                .get().uri("http://product-service/products/{id}", order.productId())
                .retrieve()
                .bodyToMono(Product.class)
                .map(product -> {
                    orders.add(order);
                    return order;
                })
                .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                    // ProductService 404 dönerse buraya düşer
                    return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Sipariş oluşturulamadı: ürün bulunamadı"));
                })
                .onErrorResume(Exception.class, e -> {
                    // Diğer hatalar
                    return Mono.error(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Product Service şu anda ulaşılamıyor"));
                });
    }
}

record Order(Long id, Long productId, Long userId) {
}

record Product(Long id, String name, Double price) {
}