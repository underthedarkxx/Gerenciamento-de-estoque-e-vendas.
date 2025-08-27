package com.sdias.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sdias.model.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Sdias Estoque") // Quem está emitindo o token
                    .withSubject(usuario.getLogin()) // Quem é o "dono" do token
                    .withExpiresAt(dataExpiracao()) // Define o tempo de validade
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validarToken(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Sdias Estoque")
                    .build()
                    .verify(tokenJWT)
                    .getSubject(); // Retorna o login do usuário se o token for válido
        } catch (JWTVerificationException exception){
            // Retorna uma string vazia se o token for inválido
            return "";
        }
    }

    private Instant dataExpiracao() {
        // Token válido por 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

