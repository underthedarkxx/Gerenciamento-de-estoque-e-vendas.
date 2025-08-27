package com.sdias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.LoginDTO;
import com.sdias.model.Usuario;
import com.sdias.service.TokenService;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody LoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Retorna um DTO simples com o token
        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }

    // DTO simples para a resposta do token
    private record TokenResponse(String token) {}
}

