package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.Funcao;
import com.scout.cs2scout.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findByIdade(Integer idade);
    List<Jogador> findByPerfilCompetitivo_Funcao(Funcao funcao);
}

