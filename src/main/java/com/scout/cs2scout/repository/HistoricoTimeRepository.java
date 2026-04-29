package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.*;
import org.springframework.data.jpa.repository.*;

public interface HistoricoTimeRepository extends JpaRepository<HistoricoTime, Long> {
    HistoricoTime findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(Jogador jogador);
}
