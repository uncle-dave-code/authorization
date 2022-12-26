package com.uncledavecode.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {


    //Endpoint level authorization

    // ---- Matcher
    // 1. AnyRequest
    // 2. RequestMatchers
    // 3. RequestMatchers with HttpMethod


    // ---- Authorization rule
    // 1. PermitAll
    // 2. DenyAll
    // 3. Authenticated
    // 4. HasRole
    // 5. HasAuthority
    // 6. Access (SpEL) - Spring Expression Language

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and().authorizeHttpRequests()
                //.anyRequest().permitAll()
//                .requestMatchers("/demo").permitAll()
//                .requestMatchers("/admin").hasRole("ADMIN")
//                .requestMatchers("/dba").hasAnyRole("DBA", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/add").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/add").authenticated()
                .and().csrf().disable().build();
    }



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic()
//                .and().authorizeHttpRequests()
//                //.anyRequest().permitAll()
//                //.anyRequest().denyAll()
//                //.anyRequest().authenticated()
//                //.anyRequest().hasRole("ADMIN")
//                //.anyRequest().hasAuthority("write")
//                .anyRequest().access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('DBA')")) //Spring Expression Language (SpEL)
//                .and().build();

//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder().encode("password123"))
                        .authorities("read","ROLE_USER")
                        .build(),
                User.withUsername("admin")
                        .password(passwordEncoder().encode("password123"))
                        .authorities("read", "write", "ROLE_ADMIN")
                        .build(),
                User.withUsername("dba")
                        .password(passwordEncoder().encode("password123"))
                        .authorities("read", "ROLE_DBA")
                        .build()
        );
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
