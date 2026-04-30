package com.scout.cs2scout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoTime> historicos;

    @OneToOne(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true)
    private PerfilCompetitivo perfilCompetitivo;
}