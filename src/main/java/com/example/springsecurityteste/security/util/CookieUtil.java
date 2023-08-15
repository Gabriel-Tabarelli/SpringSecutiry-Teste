package com.example.springsecurityteste.security.util;

import com.example.springsecurityteste.security.model.Usuario;
import jakarta.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie gerarCookie(Usuario usuario) {
        String token = JWTUtil.gerarToken(usuario);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

}
