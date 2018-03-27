package com.csipon.erp.util;

import com.csipon.erp.validation.api.MessageHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseUtil {
    @Value("{user.created}")
    private String userCreated;

    public ResponseEntity<String> getHttpResponse(MessageHeader header, String login, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        String response = String.format(userCreated, login);
        headers.set(header.getHeaderName(), response);
        return new ResponseEntity<>(response, headers, httpStatus);
    }
}
