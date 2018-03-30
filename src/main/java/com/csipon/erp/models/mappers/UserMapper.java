package com.csipon.erp.models.mappers;

import com.csipon.erp.data.RoleRepository;
import com.csipon.erp.models.User;
import com.csipon.erp.models.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleRepository roleRepository;

    public User mapToUser(UserCreateDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .lastName(userDto.getLastName())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .role(roleRepository.findById(userDto.getRoleId()).get())
                .build();
    }


}
