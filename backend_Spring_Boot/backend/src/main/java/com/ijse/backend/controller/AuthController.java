package com.ijse.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.backend.entity.User;
import com.ijse.backend.payloads.requests.LoginRequest;
import com.ijse.backend.payloads.responses.JwtResponse;
import com.ijse.backend.payloads.responses.MessageResponse;
import com.ijse.backend.repository.UserRepository;
import com.ijse.backend.security.jwt.JwtUtils;

@CrossOrigin(origins = "*")

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        
        if(userRepository.existsByUsername(user.getUsername())) {
            //payload/requsts/MessageResponse - convert responses into json format.
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken"));
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email already in use"));
        }

        //need to encrypt password before it save in the db
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        //---
        newUser.setRole("customer"); //set default role as customer in this api
        //---
        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("User created successfully."));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = userRepository.findByUsername(request.getUsername()).orElse(null);

        return ResponseEntity.ok(new JwtResponse(jwt,user.getId(),user.getUsername(),user.getEmail(), user.getRole()));
    }    
}
