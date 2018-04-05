package com.csipon.erp;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncodingTest {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String pass = "12345678";
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) passwordEncoder;
        String bPass = passwordEncoder.encode(pass);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
        String pPass = passwordEncoder.encode(pass);

        System.out.println(bPass);
        System.out.println(pPass);
        System.out.println(passwordEncoder.matches(pass, bPass));
        System.out.println(passwordEncoder.matches(pass, pPass));
    }


}
