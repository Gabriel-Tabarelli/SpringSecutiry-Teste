package com.example.springsecurityteste.security.repository;

import com.example.springsecurityteste.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByPessoa_Email(String email);

}
