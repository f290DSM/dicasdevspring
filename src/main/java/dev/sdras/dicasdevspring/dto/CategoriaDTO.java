package dev.sdras.dicasdevspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
public class CategoriaDTO {
    private Integer id;
    @NotBlank
    @NotNull
    @Length(max = 50)
    private String descricao;
}
