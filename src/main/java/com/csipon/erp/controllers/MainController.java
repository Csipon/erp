package com.csipon.erp.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String main() {
        log.debug("Try to find user");
        return "main";
    }


    @GetMapping("/login-page")
    public String loginPage() {
        log.debug("Try to find user");
        return "login";
    }

    @GetMapping("/400")
    public String badRequest() {
        return "error/400";
    }

    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }
}
