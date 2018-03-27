package com.csipon.erp.validation.api;

import org.springframework.validation.Validator;

public interface AbstractValidator extends Validator{
    default boolean isNotNull(Object obj){
        return obj != null;
    }

    default boolean validString(String str){
        return isNotNull(str) && !str.isEmpty();
    }

    default String replaceTemplate(String template, String fieldName){
        return String.format(template, fieldName);
    }
}
