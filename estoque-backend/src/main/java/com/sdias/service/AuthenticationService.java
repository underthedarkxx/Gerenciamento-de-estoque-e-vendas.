package com.sdias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sdias.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Usa o seu UsuarioRepository para encontrar o utilizador pelo login
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Dados de login inválidos."));
    }
}

