package com.resq.resqbackend.service;

import com.resq.resqbackend.entity.Ngo;
import com.resq.resqbackend.entity.User;
import com.resq.resqbackend.repository.NgoRepository;
import com.resq.resqbackend.repository.UserRepository;
import com.resq.resqbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NgoRepository ngoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // We'll configure this in config

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered";
    }

    public String registerNgo(Ngo ngo) {
        ngo.setPassword(passwordEncoder.encode(ngo.getPassword()));
        ngoRepository.save(ngo);
        return "NGO registered";
    }

    public String login(String email, String password, String role) {
        if (role.equals("USER")) {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new java.util.ArrayList<>()));
            }
        } else if (role.equals("NGO")) {
            Ngo ngo = ngoRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("NGO not found"));
            if (passwordEncoder.matches(password, ngo.getPassword())) {
                return jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(ngo.getEmail(), ngo.getPassword(), new java.util.ArrayList<>()));
            }
        }
        throw new RuntimeException("Invalid credentials");
    }
}