package com.senac.aula_api.application.services;

import com.senac.aula_api.application.dto.usuario.UsuarioCriarRequestDto;
import com.senac.aula_api.application.dto.usuario.UsuarioResponseDto;
import com.senac.aula_api.domain.entities.Usuario;
import com.senac.aula_api.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDto> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::converterParaResponseDto)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDto buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? converterParaResponseDto(usuario) : null;
    }

    public UsuarioResponseDto salvarUsuario(UsuarioCriarRequestDto dto) {
        Usuario usuario = new Usuario(dto);
        // encode senha antes de salvar
        if (usuario.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return converterParaResponseDto(usuarioSalvo);
    }

    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioCriarRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        // encode senha ao atualizar
        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }
        usuario.setCpf(new com.senac.aula_api.domain.valueobjects.CPF(dto.cpf()));
        
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return converterParaResponseDto(usuarioAtualizado);
    }

    private UsuarioResponseDto converterParaResponseDto(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf() != null ? usuario.getCpf().toString() : null,
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getStatus() != null ? usuario.getStatus().toString() : "ATIVO"
        );
    }
}
