package com.csipon.erp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(collection = "tokens")
public class RememberMeToken {
    @Id
    private String username;
    private String series;
    private String tokenValue;
    private Date date;
}
