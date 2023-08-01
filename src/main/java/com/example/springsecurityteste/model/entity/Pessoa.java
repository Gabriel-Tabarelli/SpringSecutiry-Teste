package com.example.springsecurityteste.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Pessoa {

    @Id
    private Long cpf;

    private String nome;

    private String sobreNome;

    private String email;

}
