package com.example.jwt.service;

import com.example.jwt.entity.UserEntity;
import com.example.jwt.enumeration.MessageResponseEnum;
import com.example.jwt.exception.CustomValidationException;
import com.example.jwt.model.JwtInfo;
import com.example.jwt.model.UserInfo;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.request.LoginRequest;
import com.example.jwt.request.RegisterRequest;
import com.example.jwt.util.CustomResponseUtil;
import com.example.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(request.getFullName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        userEntity = userRepository.save(userEntity);

        return CustomResponseUtil.success(userEntity);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        UserEntity userEntity = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new CustomValidationException(MessageResponseEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
        
        //autentikasi username dan password di authenticationProvider
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserInfo userInfo = new UserInfo(userEntity.getFullName(), userEntity.getEmail());

        JwtInfo jwtInfo = jwtUtil.generateAccessToken(userInfo);

        return CustomResponseUtil.success(jwtInfo);
    }
}
