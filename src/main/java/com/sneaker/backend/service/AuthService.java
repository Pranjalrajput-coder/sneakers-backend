package com.sneaker.backend.service;

import com.sneaker.backend.dto.AuthResponseDto;
import com.sneaker.backend.dto.LoginRequestDto;
import com.sneaker.backend.dto.SignupRequestDto;
import com.sneaker.backend.entities.UserEntity;
import com.sneaker.backend.repository.UserRepo;
import com.sneaker.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();

        UserEntity user = userRepo.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(email);
        return new AuthResponseDto(token, user.getEmail(), user.getUsername());
    }

    public String signup(SignupRequestDto signupRequestDto) {

        String email = signupRequestDto.getEmail();
        String phone = signupRequestDto.getPhone();

        if (userRepo.existsByEmailAndPhone(email, phone)) {
            throw new RuntimeException("User already exists");
        }
        else {
            UserEntity userEntity = new UserEntity();

            userEntity.setEmail(email);
            userEntity.setPhone(phone);
            userEntity.setUsername(signupRequestDto.getUsername());

            String password = passwordEncoder.encode(signupRequestDto.getPassword());
            userEntity.setPassword(password);

            userRepo.save(userEntity);

            // TODO: Send Notification on mail (LATER)

            return "User registered successfully";
        }
    }

}
