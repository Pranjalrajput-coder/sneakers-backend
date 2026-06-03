package com.sneaker.backend.service;

import com.sneaker.backend.dto.AuthResponseDto;
import com.sneaker.backend.dto.LoginRequestDto;
import com.sneaker.backend.dto.SignupRequestDto;
import com.sneaker.backend.entities.UserEntity;
import com.sneaker.backend.entities.role.UserRoles;
import com.sneaker.backend.repository.UserRepo;
import com.sneaker.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {

//        String email = loginRequestDto.getEmail();
//
//        UserEntity user = userRepo.findByEmail(loginRequestDto.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDto(token, user.getEmail(), user.getUsername());
    }

    public String signup(SignupRequestDto signupRequestDto) {

        String email = signupRequestDto.getEmail();
        String phone = signupRequestDto.getPhone();
        String username = signupRequestDto.getUsername();

        if (userRepo.existsByEmailAndPhone(email, phone)) {
            throw new RuntimeException("User already exists");
        }
        else {
            userRepo.save(
                    UserEntity.builder()
                    .email(email)
                    .phone(phone)
                    .username(username)
                    .userRoles(UserRoles.CUSTOMER)  // default Customer
                    .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                    .build()
            );

            // TODO: Send Notification on mail (LATER)

            return "User registered successfully";
        }
    }

}
