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
public class JogadorRequestDTO {
    private String nickname;
    private String nomeCompleto;
    private String nacionalidade;
    private Integer idade;
    private String hltvUrl;
    private Long timeId;
    private Long elo;
    private Funcao funcao;
}
