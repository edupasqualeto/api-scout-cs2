package com.scout.cs2scout.dto;

import com.scout.cs2scout.model.Funcao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogadorResponseDTO {
    private String nickname;
    private String nomeCompleto;
    private String nacionalidade;
    private Integer idade;
    private String hltvUrl;
    private String nomeTime;
    private Long elo;
    private Funcao funcao;
}
