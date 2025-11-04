package com.senac.aula_api.presentation;

import com.senac.aula_api.domain.entities.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    @Autowired
    private com.senac.aula_api.domain.repository.AdministradorRepository administradorRepository;

    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }

    @PostMapping
    public Administrador criar(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @PutMapping("/{id}")
    public Administrador atualizar(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorRepository.save(administrador);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        administradorRepository.deleteById(id);
    }
}
