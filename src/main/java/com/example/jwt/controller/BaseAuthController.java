package com.example.jwt.controller;

import com.example.jwt.model.UserInfo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;

@SecurityRequirement(name = "bearerAuth")
public class BaseAuthController {
    protected UserInfo getUserInfo(Authentication authentication) {
        return (UserInfo) authentication.getPrincipal();
    }

    protected String getUserNameLogin(Authentication authentication) {
        return this.getUserInfo(authentication).getUsername();
    }
}
