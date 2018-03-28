package com.csipon.erp.service;

import com.csipon.erp.data.UserRepository;
import com.csipon.erp.models.User;
import com.csipon.erp.models.dto.UserCreateDto;
import com.csipon.erp.models.mappers.UserMapper;
import com.csipon.erp.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreateDto userCreateDto) {
        log.debug("Create user with login = {}", userCreateDto.getLogin());
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        return userRepository.save(userMapper.mapToUser(userCreateDto));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public List<User> getUsersByRoleId(String roleId) {
        return userRepository.findUsersByRoleId(roleId);
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        return userRepository.findUsersByFirstName(firstName);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findUsersByLastName(lastName);
    }
}
