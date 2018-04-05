package com.csipon.erp.service;

import com.csipon.erp.data.RoleDetailsRepository;
import com.csipon.erp.models.RoleDetails;
import com.csipon.erp.models.dto.RoleDetailsCreateDto;
import com.csipon.erp.models.mappers.RoleDetailsMapper;
import com.csipon.erp.service.api.RoleDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleDetailsServiceImpl  implements RoleDetailsService {
    private final RoleDetailsRepository roleDetailsRepository;
    private final RoleDetailsMapper roleDetailsMapper;


    @Override
    public RoleDetails create(RoleDetailsCreateDto dto) {
        RoleDetails roleDetails = roleDetailsMapper.mapTo(dto);
        return roleDetailsRepository.save(roleDetails);
    }

    @Override
    public RoleDetails update(RoleDetails roleDetails) {
        return roleDetailsRepository.save(roleDetails);
    }

    @Override
    public RoleDetails delete(RoleDetails roleDetails) {
        roleDetailsRepository.delete(roleDetails);
        return roleDetails;
    }
}
