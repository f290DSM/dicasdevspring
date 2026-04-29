package dev.sdras.dicasdevspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "dicasdev")
@Data
public class DicasDevEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 100)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
}
