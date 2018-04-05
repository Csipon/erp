package com.csipon.erp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "user_entries")
@Data
@Builder
public class UserEntry {
    @Id
    private String id;
    private User user;
    private Map<Week, Integer> workWeek;
    private LocalDate from;
    private LocalDate to;
    private LocalDateTime lastDateModified;
}
