package com.csipon.erp.models.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String roleId;
}
