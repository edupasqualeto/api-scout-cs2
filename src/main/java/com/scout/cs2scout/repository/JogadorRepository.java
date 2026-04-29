package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @Query(
            value = """
            SELECT j FROM Jogador j
            LEFT JOIN j.perfilCompetitivo p
            LEFT JOIN j.time t
            WHERE (:funcao IS NULL OR p.funcao = :funcao)
            AND (:nacionalidade IS NULL OR j.nacionalidade = :nacionalidade)
            AND (:elo IS NULL OR p.elo >= :elo)
            AND (:idadeMin IS NULL OR j.idade >= :idadeMin)
            AND (:idadeMax IS NULL OR j.idade <= :idadeMax)
            AND (:time_id IS NULL OR t.id = :time_id)""",

            countQuery = """
            SELECT COUNT(j) FROM Jogador j
            LEFT JOIN j.perfilCompetitivo p
            LEFT JOIN j.time t
            WHERE (:funcao IS NULL OR p.funcao = :funcao)
            AND (:nacionalidade IS NULL OR j.nacionalidade = :nacionalidade)
            AND (:elo IS NULL OR p.elo >= :elo)
            AND (:idadeMin IS NULL OR j.idade >= :idadeMin)
            AND (:idadeMax IS NULL OR j.idade <= :idadeMax)
            AND (:time_id IS NULL OR t.id = :time_id)"""
    )
    Page<Jogador> buscar(
            @Param("funcao") Funcao funcao,
            @Param("elo") Long elo,
            @Param("nacionalidade") String nacionalidade,
            @Param("idadeMin") Integer idadeMin,
            @Param("idadeMax") Integer idadeMax,
            @Param("time_id") Long time_id,
            Pageable pageable
    );
}

