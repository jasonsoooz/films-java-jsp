package com.example.films.auth;

import com.example.films.auth.login.UserDTO;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
                // allow h2-console access without having to login to films app
                .antMatchers("/h2-console/**", "/signup").permitAll()
                // allow login page to access css resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // custom login page
                .loginPage("/login")
                .defaultSuccessUrl("/films")
                .failureUrl("/login?error=true")
                // need custom username parameter when not equal to username
                .usernameParameter(UserDTO.getUserParameter())
                .permitAll()
                .and()
                .logout()
                .permitAll();

        // allow h2 console to display after login
        http.headers().frameOptions().disable();
    }
}