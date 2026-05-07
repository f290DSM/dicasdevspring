package dev.sdras.dicasdevspring.repositories;


import dev.sdras.dicasdevspring.domain.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
}
