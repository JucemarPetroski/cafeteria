package com.senac.aula_api.presentation;

import com.senac.aula_api.domain.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private com.senac.aula_api.domain.repository.MenuRepository menuRepository;

    @GetMapping
    public List<Menu> listarTodos() {
        return menuRepository.findAll();
    }

    @PostMapping
    public Menu criar(@RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    @PutMapping("/{id}")
    public Menu atualizar(@PathVariable Long id, @RequestBody Menu menu) {
        menu.setId(id);
        return menuRepository.save(menu);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        menuRepository.deleteById(id);
    }
}
