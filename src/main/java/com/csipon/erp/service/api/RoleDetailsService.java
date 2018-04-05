package com.csipon.erp.service.api;

import com.csipon.erp.models.RoleDetails;
import com.csipon.erp.models.dto.RoleDetailsCreateDto;

public interface RoleDetailsService {
    RoleDetails create(RoleDetailsCreateDto dto);

    RoleDetails update(RoleDetails roleDetails);

    RoleDetails delete(RoleDetails roleDetails);
}
