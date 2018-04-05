package com.csipon.erp.models.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserEntryCreateDto {
    private String userId;
    private Integer sunday;
    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
}
