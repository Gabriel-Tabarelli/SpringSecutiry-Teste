package com.example.springsecurityteste.security.util;

import com.example.springsecurityteste.security.exception.CookieNaoEncontrado;
import com.example.springsecurityteste.security.model.Usuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

import java.util.Objects;

import static java.util.Objects.*;

public class CookieUtil {

    public static Cookie gerarCookie(Usuario usuario) {
        String token = JWTUtil.gerarToken(usuario);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public static String getToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "JWT");
        if (nonNull(cookie)) return cookie.getValue();
        throw new CookieNaoEncontrado("JWT");
    }

}
