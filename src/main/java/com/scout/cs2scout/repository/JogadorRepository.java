package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.Funcao;
import com.scout.cs2scout.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @Query("SELECT jogador FROM Jogador jogador WHERE " +
            "(jogador.perfilCompetitivo.funcao = :funcao) AND " +
            "(:nacionalidade IS NULL OR jogador.nacionalidade = :nacionalidade) AND " +
            "(:elo IS NULL OR jogador.perfilCompetitivo.elo >= :elo)")
    List<Jogador> buscar(
            @Param("funcao") Funcao funcao,
            @Param("elo") Long elo,
            @Param("nacionalidade") String nacionalidade
    );
}

