package com.example.springsecurityteste.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springsecurityteste.security.model.Usuario;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTUtil {

    @Value("${secret}")
    private static String senhaForte;

    public static String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("WEG") // Emissor do token
                .withSubject(usuario.getUsername()) // "Nome" do token
                .withIssuedAt(new Date()) // Data da emissão
                .withExpiresAt(new Date(new Date().getTime() + 1800000)) // Data da expiração
                .sign(Algorithm.HMAC256(senhaForte)); // Criptografia
    }
}
