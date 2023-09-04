package com.example.springsecurityteste.security.exception;

public class CookieNaoEncontrado extends RuntimeException{
    public CookieNaoEncontrado(String nome) {
        super("O cookie" + nome +" não foi encontrado");
    }
}
