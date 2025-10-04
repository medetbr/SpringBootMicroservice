# Spring Boot Microservice
## Özellikler

- Spring Boot ile bağımsız mikroservisler
- Eureka ile servis keşfi
- Spring Cloud Config ile merkezi konfigürasyon yönetimi
- Spring Cloud Gateway ile API Gateway
- RabbitMQ ile event-driven iletişim
- H2/ PostgreSQL veri tabanı kullanımı
- REST API ile CRUD operasyonları

## Mimari

microservice-project/<br>
├── config-server/<br>
├── eureka-server/<br>
├── api-gateway/<br>
├── product-service/<br>
├── order-service/<br>
└── user-service/

- **config-server**: Merkezi yapılandırma
- **eureka-server**: Servis keşfi
- **api-gateway**: API yönlendirme
- **product-service**: Ürün servisi
- **order-service**: Sipariş servisi
- **user-service**: Kullanıcı servisi

## Teknolojiler

- Java 21
- Spring Boot 3
- Spring Cloud (Config, Eureka, Gateway)
- RabbitMQ
- Docker

## Kurulum

1. Repo’yu klonlayın:
```bash
git clone https://github.com/medetbr/SpringBootMicroservice.git
```
## Docker kullanıyorsanız
```bash
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
## RabbitMQ Management
- http://localhost:15672/
- username: guest
- password: guest
```bash
http://localhost:8090/product-service/products/send?msg=MerhabaMedet
```
ile servise mesaj gönderebilirsiniz.


