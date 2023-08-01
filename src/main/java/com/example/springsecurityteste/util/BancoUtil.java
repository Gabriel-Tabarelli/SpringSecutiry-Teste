package com.example.springsecurityteste.util;

import com.example.springsecurityteste.model.entity.Pessoa;
import com.example.springsecurityteste.security.model.Usuario;
import com.example.springsecurityteste.security.repository.UsuarioRepository;
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

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(12345678901L);
        pessoa.setNome("Administrador");
        pessoa.setSobreNome("do Sistema");
        pessoa.setEmail("administrador@gmail.com");
        pessoa.setSenha(new BCryptPasswordEncoder().encode("123"));

        Usuario usuario = new Usuario();
        usuario.setPessoa(pessoa);
        usuario.setEnabled(true);
        usuario.setAccountNonExpired(true);
        usuario.setAccountNonLocked(true);
        usuario.setCredentialsNonExpired(true);
        usuario.setAuthorities(new ArrayList<>());

        usuarioRepository.save(usuario);
    }

}
