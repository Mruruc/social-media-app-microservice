package com.mruruc.user.controller;

import com.mruruc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;


    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isUserExist(@PathVariable Long id) {
        return ResponseEntity
                .ok(service.isUserExist(id));
    }

}
