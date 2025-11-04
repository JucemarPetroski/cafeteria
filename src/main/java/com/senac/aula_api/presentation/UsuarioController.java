package com.senac.aula_api.presentation;

import com.senac.aula_api.application.dto.usuario.UsuarioCriarRequestDto;
import com.senac.aula_api.application.dto.usuario.UsuarioResponseDto;
import com.senac.aula_api.application.services.UsuarioService;
import com.senac.aula_api.domain.entities.Usuario;
import com.senac.aula_api.domain.repository.UsuarioRepository;
import com.senac.aula_api.domain.valueobjects.EnumStatusUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios controller", description = "Controladora responsável por gerenciar os usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Método para listar todos os usuários!")
    public ResponseEntity<List<UsuarioResponseDto>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de usuário por ID", description = "Método responsável por consultar um único usuário por ID e se não existir retorna null!")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            var usuario = usuarioService.buscarPorId(id);

            if(usuario == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(usuario);

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Método responsável por criar usuário")
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioCriarRequestDto usuario){
        try {
            var usuarioSalvo = usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok(usuarioSalvo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Método responsável por atualizar usuário")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioCriarRequestDto usuario){
        try {
            var usuarioSalvo = usuarioService.atualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioSalvo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete de usuário!", description = "Método responsável por deletar um usuário")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        usuario.setStatus(EnumStatusUsuario.EXCLUIDO);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/bloquear")
    @Operation(summary = "Bloquear usuário!", description = "Método responsável por Bloquear um usuário")
    public ResponseEntity<?> atualizarBloquear(@PathVariable Long id){
        var usuario = usuarioRepository.findByIdAndStatusNot(id, EnumStatusUsuario.EXCLUIDO).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        usuario.setStatus(EnumStatusUsuario.BLOQUEADO);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquear usuário!", description = "Método responsável por Desbloquear um usuário")
    public ResponseEntity<?> atualizarDesbloquear(@PathVariable Long id){
        var usuario = usuarioRepository.findByIdAndStatusNot(id, EnumStatusUsuario.EXCLUIDO).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        usuario.setStatus(EnumStatusUsuario.ATIVO);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
