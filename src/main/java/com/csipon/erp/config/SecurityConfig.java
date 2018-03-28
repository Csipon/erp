package com.csipon.erp.config;

import com.csipon.erp.config.security.UserDetailsServiceImpl;
import com.csipon.erp.data.RememberMeTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final RememberMeTokenRepository tokenRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forgot", "/login**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error")
                .usernameParameter("login").passwordParameter("password")
                .successForwardUrl("/home")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .and()
                .rememberMe().tokenRepository(tokenRepository)
                .tokenValiditySeconds(1209600);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
