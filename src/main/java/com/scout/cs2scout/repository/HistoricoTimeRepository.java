package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface HistoricoTimeRepository extends JpaRepository<HistoricoTime, Long> {
    HistoricoTime findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(Jogador jogador);
    @Modifying
    @Query("DELETE FROM HistoricoTime h WHERE h.jogador.id = :jogadorId")
    void deleteByJogadorId(Long jogadorId);

}
