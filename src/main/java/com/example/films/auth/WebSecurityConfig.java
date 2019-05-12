package com.example.films.auth;

import com.example.films.auth.login.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

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
                // FIXME: clean up session & delete cookies
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
                .permitAll();

        // allow h2 console to display after login
        http.headers().frameOptions().disable();
    }

    @Autowired
    DataSource dataSource;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select email, password, true from User where email = ?")
                // No roles table at the moment
        .authoritiesByUsernameQuery("select email, 'roleNotUsed' from User where email = ?");
    }
}