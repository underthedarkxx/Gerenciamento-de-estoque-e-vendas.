package com.sdias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private PasswordEncoder passwordEncoder;

    // Injeta o login do admin a partir da variável de ambiente 'ADMIN_LOGIN'
    @Value("${admin.login:#{null}}")
    private String adminLogin;

    // Injeta a senha do admin a partir da variável de ambiente 'ADMIN_SENHA'
    @Value("${admin.senha:#{null}}")
    private String adminSenha;

    @Override
    public void run(String... args) throws Exception {
        // Apenas cria o usuário se as variáveis de ambiente foram definidas
        if (adminLogin != null && adminSenha != null) {
            if (usuarioRepository.findByLogin(adminLogin).isEmpty()) {
                Usuario proprietaria = new Usuario();
                proprietaria.setLogin(adminLogin);
                proprietaria.setSenha(passwordEncoder.encode(adminSenha));
                proprietaria.setRole("ROLE_ADMIN");
                usuarioRepository.save(proprietaria);
                System.out.println(">>> Usuário admin inicial criado com sucesso!");
            }
        } else {
            System.out.println(">>> Variáveis de ambiente ADMIN_LOGIN e ADMIN_SENHA não definidas. Nenhum usuário inicial foi criado.");
        }
    }
}