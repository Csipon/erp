package com.csipon.erp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "role_details")
public class RoleDetails {
    @Id
    private String id;
    private Role role;
    private Double salaryPerHour;
    private Double salary;
}
