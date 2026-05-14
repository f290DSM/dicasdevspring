package dev.sdras.dicasdevspring.services;

import dev.sdras.dicasdevspring.config.exception.InvalidInputException;
import dev.sdras.dicasdevspring.config.exception.NotFoundException;
import dev.sdras.dicasdevspring.domain.CategoriaEntity;
import dev.sdras.dicasdevspring.dto.CategoriaDTO;
import dev.sdras.dicasdevspring.repositories.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final ModelMapper mapper;
    Type listType = new TypeToken<List<CategoriaDTO>>() {
    }.getType();

    public CategoriaService(CategoriaRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        try {
            CategoriaEntity categoria = mapper.map(categoriaDTO, CategoriaEntity.class);
            CategoriaEntity entity = repository.save(categoria);
            return mapper.map(entity, CategoriaDTO.class);
        } catch (Exception e) {
            throw new InvalidInputException("Erro ao salvar categoria: " + e.getMessage());
        }
    }

    public List<CategoriaDTO> findAll() {
        List<CategoriaEntity> categoriesEntity = repository.findAll();
        if (categoriesEntity.isEmpty())
            throw new NotFoundException("Sem categorias cadastradas.");

        return mapper.map(categoriesEntity, listType);
    }

    public CategoriaDTO findById(Integer id) {
        CategoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com id: " + id));
        return mapper.map(entity, CategoriaDTO.class);
    }

    public CategoriaDTO update(Integer id, CategoriaDTO categoriaDTO) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com id: " + id));
        try {
            CategoriaEntity categoria = mapper.map(categoriaDTO, CategoriaEntity.class);
            categoria.setId(id);
            CategoriaEntity entity = repository.save(categoria);
            return mapper.map(entity, CategoriaDTO.class);
        } catch (Exception e) {
            throw new InvalidInputException("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void delete(Integer id) {
        CategoriaEntity existing = repository.
                findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com id: " + id));

        repository.delete(existing);
    }



}
