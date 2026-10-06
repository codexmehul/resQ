package com.resq.resqbackend.controller;

import com.resq.resqbackend.dto.LoginRequest;
import com.resq.resqbackend.entity.Ngo;
import com.resq.resqbackend.entity.User;
import com.resq.resqbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/register/ngo")
    public ResponseEntity<String> registerNgo(@RequestBody Ngo ngo) {
        return ResponseEntity.ok(authService.registerNgo(ngo));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request.getEmail(), request.getPassword(), request.getRole()));
    }
}