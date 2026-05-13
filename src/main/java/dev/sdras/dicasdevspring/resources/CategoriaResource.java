package dev.sdras.dicasdevspring.resources;

import dev.sdras.dicasdevspring.domain.CategoriaEntity;
import dev.sdras.dicasdevspring.dto.CategoriaDTO;
import dev.sdras.dicasdevspring.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.type.TypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

    private final CategoriaRepository repository;

    private final ModelMapper modelMapper;

    Type listType = new TypeToken<List<CategoriaDTO>>() {}.getType();

    public CategoriaResource(CategoriaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaEntity> categories = repository.findAll();
        if (categories.isEmpty())  return ResponseEntity.notFound().build();
        List<CategoriaDTO> categorias = modelMapper.map(categories, listType);
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO dto) {
        CategoriaEntity entity = repository
                .save(modelMapper.map(dto, CategoriaEntity.class));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
