package com.senac.aula_api.domain.repository;

import com.senac.aula_api.domain.entities.Usuario;
import com.senac.aula_api.domain.valueobjects.EnumStatusUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    Optional<Usuario> findByIdAndStatusNot(Long id, EnumStatusUsuario status);
    Optional<Usuario> findByEmail(String email);
}
