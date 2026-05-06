package com.scout.cs2scout.dto;

import com.scout.cs2scout.model.Funcao;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogadorUpdateDTO {
    private String nickname;
    private String nomeCompleto;
    private String nacionalidade;

    @Min(value = 12, message = "Idade mínima é 12")
    private Integer idade;
    private String hltvUrl;
    private Long timeId;

    @Min(value = 0, message = "Elo não pode ser negativo")
    private Long elo;
    private Funcao funcao;
}
