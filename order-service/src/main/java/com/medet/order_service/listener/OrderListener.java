package com.medet.order_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    // Kuyruğun adını belirtiyoruz
    @RabbitListener(queues = "order-queue")
    public void consumeMessage(String message) {
        System.out.println("📥 Order-service mesaj aldı: " + message);

        // Burada istersen DB’ye kaydedebilirsin veya başka işlem yapabilirsin
    }
}
