package com.scout.cs2scout.service;

import com.scout.cs2scout.exception.RecursoNaoEncontradoException;
import com.scout.cs2scout.model.Jogador;
import com.scout.cs2scout.repository.JogadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository repository;

    public Jogador salvar(Jogador jogador) {
        return repository.save(jogador);
    }

    public List<Jogador> listar() {
        return repository.findAll();
    }

    public Jogador buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void remover(Long id) { repository.deleteById(id); }

    @Transactional
    public void atualizar(Long id, Jogador jogador) {
        Jogador novoJogador = buscarPorId(id);
        if (novoJogador != null) {
            novoJogador.setNickname(jogador.getNickname());
            novoJogador.setIdade(jogador.getIdade());
            novoJogador.setFuncao(jogador.getFuncao());
            novoJogador.setRating(jogador.getRating());
        }
    }
}