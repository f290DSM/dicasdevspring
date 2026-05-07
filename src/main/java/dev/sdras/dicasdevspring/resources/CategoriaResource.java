package dev.sdras.dicasdevspring.resources;

import dev.sdras.dicasdevspring.domain.CategoriaEntity;
import dev.sdras.dicasdevspring.dto.CategoriaDTO;
import dev.sdras.dicasdevspring.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

    private final CategoriaRepository repository;

    public CategoriaResource(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaEntity> categories = repository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CategoriaDTO> categorias = categories
                .stream()
                .map(entity -> new CategoriaDTO(
                        entity.getId(),
                        entity.getDescricao())
                ).collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO dto) {
        //TODO: Criar mapeamento dinamico com modelmapper
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setDescricao(dto.getDescricao());
        CategoriaEntity entity = repository.save(categoriaEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
