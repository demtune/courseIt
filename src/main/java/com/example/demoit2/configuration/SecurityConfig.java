package com.example.demoit2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/api/users/**").hasAnyRole("ADMIN")
                .antMatchers("/api/collections/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/comments/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/items/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/likes/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/tags/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/topics/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/login/", "/api/registration/", "/api/collections").permitAll() //
                .and().httpBasic();
    }
}
