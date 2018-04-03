package com.csipon.erp.controllers;

import com.csipon.erp.models.User;
import com.csipon.erp.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EntityController {
    private final UserService userService;

    @GetMapping(value = "/profile")
    public String profile(Map<String, Object> model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.put("user", userService.getUserById(user.getId()));
        return "user";
    }
}
