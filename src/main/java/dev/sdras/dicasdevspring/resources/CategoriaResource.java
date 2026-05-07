package dev.sdras.dicasdevspring.resources;

import dev.sdras.dicasdevspring.domain.CategoriaEntity;
import dev.sdras.dicasdevspring.repositories.CategoriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

    private final CategoriaRepository repository;

    public CategoriaResource(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CategoriaEntity> findAll() {
        return repository.findAll();
    }
}
