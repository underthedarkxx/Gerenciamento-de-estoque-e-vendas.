package com.sdias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sdias.model.Usuario;
import com.sdias.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetaremos o codificador de senha

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe um usuário com o login "sdias"
        if (usuarioRepository.findByLogin("sdias").isEmpty()) {
            Usuario proprietaria = new Usuario();
            proprietaria.setLogin("sdias");
            // NUNCA salve a senha em texto puro! Use o PasswordEncoder.
            proprietaria.setSenha(passwordEncoder.encode("senhaSuperSecreta123")); 
            
            usuarioRepository.save(proprietaria);
            System.out.println(">>> Usuário da proprietária criado com sucesso!");
        }
    }
}