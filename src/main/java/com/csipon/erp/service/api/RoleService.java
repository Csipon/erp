package com.csipon.erp.service.api;

import com.csipon.erp.models.Role;
import com.csipon.erp.models.dto.RoleCreateDto;

public interface RoleService {

    Role create(RoleCreateDto dto);

    Role update(Role role);

    Role delete(String roleName);
}
