package com.example.jwt.controller;

import com.example.jwt.model.UserInfo;
import com.example.jwt.util.CustomResponseUtil;
import com.example.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloController extends BaseAuthController {
    private final JwtUtil jwtUtil;

    @GetMapping("/world")
    public ResponseEntity<?> helloWorld() {
        return CustomResponseUtil.success("heloo world");
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> userInfo(Authentication authentication) {
        UserInfo userInfo = this.getUserInfo(authentication);

        return CustomResponseUtil.success(userInfo);
    }

    @GetMapping("/username")
    public ResponseEntity<?> userNameLogin(Authentication authentication) {
        String username = this.getUserNameLogin(authentication);

        return CustomResponseUtil.success(username);
    }
}
