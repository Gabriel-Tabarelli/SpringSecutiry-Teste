package com.example.springsecurityteste.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.springsecurityteste.model.entity.Pessoa;
import com.example.springsecurityteste.security.exception.CookieNaoEncontrado;
import com.example.springsecurityteste.security.model.Usuario;
import com.example.springsecurityteste.security.util.CookieUtil;
import com.example.springsecurityteste.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class Filtro extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (rotaPrivada(request.getRequestURI())) {
            try {
                String token = CookieUtil.getToken(request);
                Usuario user = JWTUtil.getUsuario(token);
                response.addCookie(CookieUtil.gerarCookie(user)); // Regenerar o cookie com a data de expiração atualizada}
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                        null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTDecodeException e) {
                response.setStatus(401);
                response.getWriter().write("Token inválido");
                return;
            } catch (CookieNaoEncontrado e) {
                response.setStatus(401);
                response.getWriter().write(e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean rotaPrivada(String url) {
        return url.startsWith("/teste/logado");
    }

}