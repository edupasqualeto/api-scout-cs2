package com.scout.cs2scout.dto;

import com.scout.cs2scout.model.Funcao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogadorRequestDTO {

    @NotBlank(message = "Nickname é obrigatório")
    private String nickname;

    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;

    @NotBlank(message = "Nacionalidade é obrigatória")
    private String nacionalidade;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 12, message = "Idade mínima é 12")
    private Integer idade;

    private String hltvUrl;

    private Long timeId;

    @NotNull(message = "Elo é obrigatório")
    @Min(value = 0, message = "Elo não pode ser negativo")
    private Long elo;

    private Funcao funcao;
}
