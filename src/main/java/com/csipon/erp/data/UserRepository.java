package com.csipon.erp.data;

import com.csipon.erp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByLogin(String login);

    List<User> findUsersByFirstName(String firstName);

    List<User> findUsersByLastName(String lastName);

    List<User> findUsersByRoleId(String roleId);
}
