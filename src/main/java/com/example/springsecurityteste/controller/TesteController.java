package com.example.springsecurityteste.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teste")
@AllArgsConstructor
public class TesteController {

//    private final UsuarioRepository repository;

//    @PostMapping
//    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        usuario.getPessoa().setSenha(encoder.encode(usuario.getPassword()));
//        return ResponseEntity.ok(repository.save(usuario));
//    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Acessou");
    }

    @GetMapping("/logado")
    public ResponseEntity<String> getLogado() {
        return ResponseEntity.ok("Acessou logado");
    }

}
