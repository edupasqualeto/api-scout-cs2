package com.scout.cs2scout.service;

import com.scout.cs2scout.model.*;
import com.scout.cs2scout.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class JogadorService {
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private JogadorRepository repository;
    @Autowired
    private HistoricoTimeRepository historicoRepository;

    @Transactional
    public Jogador salvar(Jogador jogador) {

        if (jogador.getPerfilCompetitivo() != null) {
            jogador.getPerfilCompetitivo().setJogador(jogador);
        }

        if (jogador.getTime() != null && jogador.getTime().getId() != null) {
            Time time = timeRepository.findById(jogador.getTime().getId())
                    .orElseThrow(() -> new RuntimeException("Time não encontrado"));

            jogador.setTime(time);
        }

        Jogador salvo = repository.save(jogador);
        repository.flush();

        if (salvo.getTime() != null) {
            atualizarHistorico(salvo, salvo.getTime());
        }

        return salvo;
    }

    public List<Jogador> listar() { return repository.findAll(); }

    public Jogador buscarPorId(Long id) { return repository.findById(id).orElse(null); }

    public Page<Jogador> buscarComFiltro(Funcao funcao, Long elo,
                                         String nacionalidade, Integer idadeMin,
                                         Integer idadeMax, Long time_id, Pageable pageable) {
            return repository.buscar(funcao, elo, nacionalidade, idadeMin, idadeMax, time_id, pageable);
    }

    @Transactional
    public void deletar(Long id) {
        historicoRepository.deleteByJogadorId(id);
        historicoRepository.flush();

        repository.deleteById(id);
    }

    @Transactional
    public void atualizar(Long id, Jogador jogador) {
        Jogador novoJogador = buscarPorId(id);

        if (novoJogador != null) {
            if (jogador.getNickname() != null) {
                novoJogador.setNickname(jogador.getNickname());
            }
            if (jogador.getNomeCompleto() != null) {
                novoJogador.setNomeCompleto(jogador.getNomeCompleto());
            }
            if (jogador.getNacionalidade() != null) {
                novoJogador.setNacionalidade(jogador.getNacionalidade());
            }
            if (jogador.getIdade() != null) {
                novoJogador.setIdade(jogador.getIdade());
            }
            if (jogador.getHltvUrl() != null) {
                novoJogador.setHltvUrl(jogador.getHltvUrl());
            }

            Long timeAtualId;
            if (jogador.getTime() != null) {
                Long novoTimeId = jogador.getTime().getId();

                if(novoJogador.getTime() != null) {
                    timeAtualId = novoJogador.getTime().getId();
                } else { timeAtualId = null; }

                if (!Objects.equals(novoTimeId, timeAtualId)) {
                    Time novoTime = timeRepository.findById(novoTimeId)
                            .orElseThrow();

                    novoJogador.setTime(novoTime);
                    atualizarHistorico(novoJogador, novoTime);
                }
            }

            if (jogador.getPerfilCompetitivo() != null) {
                if (novoJogador.getPerfilCompetitivo() == null) {
                    PerfilCompetitivo perfil = new PerfilCompetitivo();
                    perfil.setJogador(novoJogador);
                    novoJogador.setPerfilCompetitivo(perfil);
                }
                if(jogador.getPerfilCompetitivo().getFuncao() != null) {
                    novoJogador.getPerfilCompetitivo().setFuncao(jogador.getPerfilCompetitivo().getFuncao());
                }
                if(jogador.getPerfilCompetitivo().getElo() != null) {
                    novoJogador.getPerfilCompetitivo().setElo(jogador.getPerfilCompetitivo().getElo());
                }
            }
        }
    }

    private void atualizarHistorico(Jogador jogador, Time novoTime) {

        HistoricoTime historicoAtual =
                historicoRepository.findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(jogador);

        if(historicoAtual != null) {
            historicoAtual.setDataSaida(LocalDate.now());
        }

        HistoricoTime novo = new HistoricoTime();
        novo.setJogador(jogador);
        novo.setTime(novoTime);
        novo.setDataEntrada(LocalDate.now());

        historicoRepository.save(novo);
    }
}