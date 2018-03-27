package com.csipon.erp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "users")
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Role role;

    public User(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }
}
