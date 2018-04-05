package com.csipon.erp.models.mappers;

import com.csipon.erp.data.RoleRepository;
import com.csipon.erp.models.RoleDetails;
import com.csipon.erp.models.dto.RoleDetailsCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDetailsMapper {
    private final RoleRepository roleRepository;

    public RoleDetails mapTo(RoleDetailsCreateDto dto) {
        return RoleDetails
                .builder()
                .role(roleRepository.findByRole(dto.getRoleName()))
                .salary(dto.getSalary())
                .salaryPerHour(dto.getSalaryPerHour())
                .build();
    }
}
