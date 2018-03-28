package com.csipon.erp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private String role;
    private String description;
}
