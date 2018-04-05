package com.csipon.erp.service;

import com.csipon.erp.data.RoleRepository;
import com.csipon.erp.models.Role;
import com.csipon.erp.models.dto.RoleCreateDto;
import com.csipon.erp.models.mappers.RoleMapper;
import com.csipon.erp.service.api.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public Role create(RoleCreateDto dto) {
        Role role = roleMapper.mapTo(dto);
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role delete(String roleName) {
        Role role = roleRepository.findByRole(roleName);
        roleRepository.delete(role);
        return role;
    }
}
