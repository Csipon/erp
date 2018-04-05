package com.csipon.erp.models.mappers;

import com.csipon.erp.models.Role;
import com.csipon.erp.models.dto.RoleCreateDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role mapTo(RoleCreateDto dto) {
        return Role
                .builder()
                .role(dto.getRoleName())
                .description(dto.getDescription())
                .build();
    }
}
