package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.HistoricoTime;
import com.scout.cs2scout.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HistoricoTimeRepository extends JpaRepository<HistoricoTime, Long> {
    Optional<HistoricoTime> findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(Jogador jogador);
}
