package dev.sdras.dicasdevspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
public class CategoriaDTO {
    private Integer id;
    @NotBlank
    @NotNull
    @Length(max = 50)
    private String descricao;
}
