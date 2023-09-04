package com.example.springsecurityteste.security;

import com.example.springsecurityteste.security.service.JpaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
@EnableMethodSecurity // Habilita o @PreAuthorize nos metodos do controller
public class Configuracao {

    private final JpaService jpaService;

    @Autowired
    public void configurer(AuthenticationManagerBuilder amb) throws Exception {
        amb.userDetailsService(jpaService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                .requestMatchers(HttpMethod.GET, "/teste/logado").authenticated()
//                .requestMatchers(HttpMethod.GET, "/teste/logado/admin").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.GET, "/teste/logado/cliente").hasAuthority("CLIENTE")
//                .requestMatchers(HttpMethod.GET, "/teste/logado/vendedor").hasAuthority("VENDEDOR")
//                .anyRequest().permitAll()
//        ); // Tem como colocar isso nos metodos do controller
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(new Filtro(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}


