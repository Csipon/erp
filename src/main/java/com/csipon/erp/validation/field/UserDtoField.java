package com.csipon.erp.validation.field;

import com.csipon.erp.validation.api.DtoField;

public enum UserDtoField implements DtoField{
    LOGIN("login", "Login"),
    PASSWORD("password", "Password"),
    FIRST_NAME("firstName", "First Name"),
    MIDDLE_NAME("middleName", "Middle Name"),
    LAST_NAME("lastName", "Last Name"),
    ROLE("role", "User Role");

    UserDtoField(String dtoName, String errorName) {
        this.name = dtoName;
        this.errorName = errorName;
    }

    private final String name;
    private final String errorName;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getErrorName() {
        return this.errorName;
    }
}
