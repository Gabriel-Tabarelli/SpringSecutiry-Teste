package com.example.springsecurityteste.security;

import com.example.springsecurityteste.security.model.Usuario;
import com.example.springsecurityteste.security.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody Login login,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(login.username, login.password());
        Authentication authentication = authenticationManager.authenticate(token);

        if (authentication.isAuthenticated()) {
            Cookie cookie = CookieUtil.gerarCookie((Usuario) authentication.getPrincipal());
            response.addCookie(cookie);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(401).build();
    }

    private record Login(String username, String password) {}

}
