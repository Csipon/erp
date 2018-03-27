package com.csipon.erp.config.security;

import com.csipon.erp.data.UserRepository;
import com.csipon.erp.models.Role;
import com.csipon.erp.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);
        if (null == user) {
            throw new UsernameNotFoundException("No user present with login : " + login);
        } else {
            Role role = user.getRole();
            List<String> userRoles = new ArrayList<>();
            userRoles.add(role.getRole());
            log.info("User successful find with login  : " + login);
            return new UserDetailsImpl(user, userRoles);
        }
    }
}
