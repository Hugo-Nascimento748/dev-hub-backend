package com.devhub.api.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/me")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            // Se não houver ninguém logado, retorna um erro simples
            return Collections.singletonMap("error", "Não autenticado");
        }
        // Retorna todos os atributos que o GitHub nos enviou
        return principal.getAttributes();
    }
}