package com.example.mindmap.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.net.PasswordAuthentication;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER")
//                .and().withUser("bookMap").password("bookMap").roles("USER");
//
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 解决POST请求403错误
            .authorizeRequests()
            .anyRequest().permitAll(); // 允许
    }



}
