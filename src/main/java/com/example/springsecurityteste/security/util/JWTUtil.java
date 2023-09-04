package com.example.springsecurityteste.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springsecurityteste.security.model.Usuario;
import com.example.springsecurityteste.security.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static String senhaForte = "c127a7b6adb013a5ff879ae71afa62afa4b4ceb72afaa54711dbcde67b6dc325";
    private static UsuarioRepository repository;

    @Autowired
    public JWTUtil(UsuarioRepository repository) {
        JWTUtil.repository = repository;
    }

    public static String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("WEG") // Emissor do token
                .withSubject(usuario.getUsername()) // "Nome" do token
                .withIssuedAt(new Date()) // Data da emissão
                .withExpiresAt(new Date(new Date().getTime() + 1800000)) // Data da expiração
                .sign(Algorithm.HMAC256(senhaForte)); // Criptografia
    }

    public static Usuario getUsuario(String token) {
        String username = JWT.decode(token).getSubject();
        return repository.findByPessoa_Email(username);
    }

}
