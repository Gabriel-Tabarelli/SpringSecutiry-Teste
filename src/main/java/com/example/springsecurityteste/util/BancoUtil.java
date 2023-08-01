package com.example.springsecurityteste.util;

import com.example.springsecurityteste.model.entity.Usuario;
import com.example.springsecurityteste.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class BancoUtil {

    private final UsuarioRepository usuarioRepository;

    @PostConstruct
    public void popularBanco() {
        usuarioRepository.deleteAll();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setNomeCompleto("Administrador");
        usuario.setPassword(encoder.encode("123"));
        usuario.setEnabled(true);
        usuario.setAccountNonExpired(true);
        usuario.setAccountNonLocked(true);
        usuario.setCredentialsNonExpired(true);
        usuario.setAuthorities(new ArrayList<>());
        usuarioRepository.save(usuario);
    }

}
