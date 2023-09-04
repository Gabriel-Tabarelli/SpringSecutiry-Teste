package com.example.springsecurityteste.security.model;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {

    ADMIN, VENDEDOR, CLIENTE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
