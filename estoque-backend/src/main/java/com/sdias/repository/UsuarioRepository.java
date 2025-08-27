package com.sdias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sdias.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar o usuário pelo login
    Optional<Usuario> findByLogin(String login);
}
