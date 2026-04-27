package com.scout.cs2scout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCompetitivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long elo;

    @Enumerated(EnumType.STRING)
    private Funcao funcao;

    @OneToOne
    @JoinColumn(name = "jogador_id")
    @JsonIgnore
    private Jogador jogador;
}