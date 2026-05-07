package dev.sdras.dicasdevspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "categorias")
@Entity
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String descricao;
}
