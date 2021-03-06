package com.csipon.erp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "logout", required = false) String logout,
                        HttpServletRequest request, Model model) throws Throwable {
//        if (logout != null) {
//            model.addAttribute("msg", "You've been logged out successfully.");
//        }else if (request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null){
//            throw (Throwable) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//        }
        log.info("HELP ME PLEASE!");
        return "login";
    }
}
