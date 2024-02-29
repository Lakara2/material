package com.mat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder.encode("adminpass")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder.encode("userpass")).roles("USER")
                .and()
                .withUser("client").password(passwordEncoder.encode("clientpass")).roles("CLIENT");
    }

    public void addUser(AuthenticationManagerBuilder auth, String username, String password, String... roles) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(username).password(passwordEncoder.encode(password)).roles(roles);
    }

}


