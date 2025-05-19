package com.RaduRadel.GymMate.GymMate.config;

import com.RaduRadel.GymMate.GymMate.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired private UserRepository userRepo;

    // 1 Load users from DB
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No user: " + username));
            // build a Spring Security UserDetails
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().replace("ROLE_", ""))
                    .build();
        };
    }

    // 2 Password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3 Hook service + encoder into auth provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    // 4 Security rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // public
                        .requestMatchers("/", "/login", "/register", "/css/**", "/js/**").permitAll()
                        // only ADMIN can reach /admin/**
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // MEMBER & ADMIN can reach /member/**
                        .requestMatchers("/member/**").hasAnyRole("MEMBER","ADMIN")
                        // schedule/trainers (and any other) need at least MEMBER
                        .requestMatchers("/schedule", "/trainers").hasAnyRole("MEMBER","ADMIN")
                        // everything else requires auth
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
