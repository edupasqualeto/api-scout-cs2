package com.scout.cs2scout.service;

import com.scout.cs2scout.model.*;
import com.scout.cs2scout.repository.HistoricoTimeRepository;
import com.scout.cs2scout.repository.JogadorRepository;
import com.scout.cs2scout.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private JogadorRepository repository;
    @Autowired
    private HistoricoTimeRepository historicoRepository;

    public Jogador buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Jogador salvar(Jogador jogador) {

        if (jogador.getPerfilCompetitivo() != null) {
            jogador.getPerfilCompetitivo().setJogador(jogador);
        }

        if (jogador.getTime() != null && jogador.getTime().getId() != null) {
            Time time = timeRepository.findById(jogador.getTime().getId())
                    .orElseThrow(() -> new RuntimeException("Time não encontrado"));

            jogador.setTime(time);
        }

        if (jogador.getTime() != null) {
            atualizarHistorico(jogador, jogador.getTime());
        }

        return repository.save(jogador);
    }
    public List<Jogador> listar() {
        return repository.findAll();
    }

    public List<Jogador> buscarPorFuncao(Funcao funcao) {
        return repository.findByPerfilCompetitivo_Funcao(funcao);
    }

    public void remover(Long id) { repository.deleteById(id); }

    @Transactional
    public void atualizar(Long id, Jogador jogador) {
        Jogador novoJogador = buscarPorId(id);
        if (novoJogador != null) {
            novoJogador.setNickname(jogador.getNickname());
            novoJogador.setNomeCompleto(jogador.getNomeCompleto());
            novoJogador.setNacionalidade(jogador.getNacionalidade());
            novoJogador.setIdade(jogador.getIdade());
            novoJogador.setHltvUrl(jogador.getHltvUrl());

            if (jogador.getTime() != null && !jogador.getTime().getId().equals(novoJogador.getTime().getId())) {

                Time novoTime = timeRepository.findById(jogador.getTime().getId())
                        .orElseThrow();

                novoJogador.setTime(novoTime);

                atualizarHistorico(novoJogador, novoTime);
            }

            if (jogador.getPerfilCompetitivo() != null) {
                if (novoJogador.getPerfilCompetitivo() == null) {
                    PerfilCompetitivo perfil = new PerfilCompetitivo();
                    perfil.setJogador(novoJogador);
                    novoJogador.setPerfilCompetitivo(perfil);
                }
                PerfilCompetitivo perfil = novoJogador.getPerfilCompetitivo();
                perfil.setFuncao(jogador.getPerfilCompetitivo().getFuncao());
                perfil.setElo(jogador.getPerfilCompetitivo().getElo());
            }
        }
    }

    private void atualizarHistorico(Jogador jogador, Time novoTime) {

        Optional<HistoricoTime> historicoAtual =
                historicoRepository.findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(jogador);

        historicoAtual.ifPresent(h -> {
            h.setDataSaida(LocalDate.now());
        });

        HistoricoTime novo = new HistoricoTime();
        novo.setJogador(jogador);
        novo.setTime(novoTime);
        novo.setDataEntrada(LocalDate.now());

        historicoRepository.save(novo);
    }
}