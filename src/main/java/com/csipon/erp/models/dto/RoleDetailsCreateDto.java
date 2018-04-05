package com.csipon.erp.models.dto;

import lombok.Data;

@Data
public class RoleDetailsCreateDto {
    private String roleName;
    private Double salaryPerHour;
    private Double salary;
}
