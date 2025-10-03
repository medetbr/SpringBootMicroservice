package com.medet.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final List<User> users = List.of(
            new User(1L, "Ali"),
            new User(2L, "Ayşe")
    );

    @GetMapping
    public List<User> getAll() { return users; }
}

record User(Long id, String name) {}

