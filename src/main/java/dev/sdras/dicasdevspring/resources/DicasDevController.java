package dev.sdras.dicasdevspring.resources;

import dev.sdras.dicasdevspring.domain.DicasDevEntity;
import dev.sdras.dicasdevspring.repositories.DicasdevRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/dicasdev")
public class DicasDevController {

    final DicasdevRepository repository;

    public DicasDevController(DicasdevRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody DicasDevEntity dicasDevEntity) {
        DicasDevEntity entity = repository.save(dicasDevEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        System.out.println("Excluir dicasdev");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DicasDevEntity> get(@PathVariable Integer id) {
        Optional<DicasDevEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DicasDevEntity>> getAll() {
        var dicasDevEntity = new DicasDevEntity();
        dicasDevEntity.setId(1);
        dicasDevEntity.setId(new Random().nextInt(100));
        dicasDevEntity.setDescricao("Mussum ipsum cacildis vidis litrus abertis.");
        dicasDevEntity.setTitulo("Mussum Ipsum");

        var dicasDevEntity2 = new DicasDevEntity();
        dicasDevEntity2.setId(1);
        dicasDevEntity2.setId(new Random().nextInt(100));
        dicasDevEntity2.setDescricao("Lorem ipsum dolor sit amet...");
        dicasDevEntity2.setTitulo("Lorem Ipsum");
        return ResponseEntity.ok().body(Arrays.asList(dicasDevEntity, dicasDevEntity2));
    }

}
