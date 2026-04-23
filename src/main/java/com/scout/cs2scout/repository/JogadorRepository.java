package com.scout.cs2scout.repository;

import com.scout.cs2scout.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}