package com.scout.cs2scout.model;

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
    private Jogador jogador;
}