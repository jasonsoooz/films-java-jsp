package com.example.films.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // disable csrf protection temporarily as delete
                // post doesn't have csrf token so will
                // result in 403 response
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                // Use default login page
                .formLogin()
                .defaultSuccessUrl("/films")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}