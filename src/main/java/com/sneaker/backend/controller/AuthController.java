package com.sneaker.backend.controller;

import com.sneaker.backend.dto.AuthResponseDto;
import com.sneaker.backend.dto.LoginRequestDto;
import com.sneaker.backend.dto.SignupRequestDto;
import com.sneaker.backend.service.AuthService;
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

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignupRequestDto signupRequestDto){
       try {
           return ResponseEntity.ok(authService.signup(signupRequestDto));
       } catch (Exception e) {
           return ResponseEntity.badRequest().build();
       }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
       try {
           return ResponseEntity.ok(authService.login(loginRequestDto));
       }
       catch (Exception e){
           return ResponseEntity.notFound().build();
       }
    }

}
