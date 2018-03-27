package com.csipon.erp.handler;

import com.csipon.erp.validation.api.MessageHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Component
@Slf4j
public class BindingResultHandler {

    public ResponseEntity<?> handle(BindingResult bindingResult) {
        HttpHeaders errors = new HttpHeaders();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            errors.set(MessageHeader.VALIDATION_MESSAGE.getHeaderName(), objectError.getDefaultMessage());
            log.error("Validation error was found, error code - " + objectError.getCode());
        }
        return new ResponseEntity<>(errors, HttpStatus.EXPECTATION_FAILED);
    }

}
