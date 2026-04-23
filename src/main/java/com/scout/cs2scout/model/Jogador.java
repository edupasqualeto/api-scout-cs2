package com.scout.cs2scout.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String nomeCompleto;
    private String nacionalidade;
    private Integer idade;
    private String hltvUrl;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    @OneToOne(mappedBy = "jogador", cascade = CascadeType.ALL)
    private PerfilCompetitivo perfilCompetitivo;
}