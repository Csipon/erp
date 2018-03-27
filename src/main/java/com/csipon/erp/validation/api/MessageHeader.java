package com.csipon.erp.validation.api;

public enum MessageHeader {
    SUCCESS_MESSAGE("successMessage"),
    VALIDATION_MESSAGE("validationMessage"),
    ERROR_MESSAGE("errorMessage");

    private final String headerName;

    MessageHeader(String name) {
        this.headerName = name;
    }

    public String getHeaderName() {
        return this.headerName;
    }
}
