package dev.sdras.dicasdevspring.repositories;


import dev.sdras.dicasdevspring.domain.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    Optional<CategoriaEntity> findByDescricaoContainingIgnoreCase(String descricao);
}
