package com.csipon.erp.controllers;

import com.csipon.erp.handler.BindingResultHandler;
import com.csipon.erp.models.User;
import com.csipon.erp.models.dto.UserCreateDto;
import com.csipon.erp.service.api.UserService;
import com.csipon.erp.util.ResponseUtil;
import com.csipon.erp.validation.UserValidator;
import com.csipon.erp.validation.api.MessageHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final BindingResultHandler bindingResultHandler;
    private final ResponseUtil responseUtil;

    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> registerUser(UserCreateDto userDto, BindingResult bindingResult){
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()){
            log.debug("Validation error during register user", bindingResult.getAllErrors().stream().findFirst().get().toString());
            return bindingResultHandler.handle(bindingResult);
        }
        User user = userService.create(userDto);
        return responseUtil.getHttpResponse(MessageHeader.SUCCESS_MESSAGE, user.getLogin(), HttpStatus.OK);
    }

}
