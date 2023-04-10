package com.italloramon.springsecurity.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/contact", "/notices", "/register").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        return http.build();

/*
        http.authorizeHttpRequests()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        http.authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
*/
    }

/*    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .authorities("read")
                .build();

        *//* Approach 2 where we use NoOpPasswordEncoder Bean while creating the user details *//*
        UserDetails admin = User.withUsername("admin")
                .password("123")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("123")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/
/*    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}




