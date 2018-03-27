package com.csipon.erp.service.api;

import com.csipon.erp.models.User;
import com.csipon.erp.models.dto.UserCreateDto;

import java.util.List;

public interface UserService {
    User createUser(UserCreateDto userCreateDto);

    User updateUser(User user);

    User getUserByLogin(String login);

    List<User> getUsersByRoleId(String roleId);

    List<User> getUsersByFirstName(String firstName);

    List<User> getUsersByLastName(String lastName);
}
