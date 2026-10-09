package com.scout.cs2scout.service;

import com.scout.cs2scout.dto.JogadorRequestDTO;
import com.scout.cs2scout.dto.JogadorResponseDTO;
import com.scout.cs2scout.dto.JogadorUpdateDTO;
import com.scout.cs2scout.exception.ResourceNotFoundException;
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
    public JogadorResponseDTO salvar(JogadorRequestDTO jogadorRequestDTO) {
        Jogador jogador = JogadorDTOtoJogador(jogadorRequestDTO);

        if (jogador.getPerfilCompetitivo() != null) {
            jogador.getPerfilCompetitivo().setJogador(jogador);
        }

        if (jogadorRequestDTO.getTimeId() != null) {
            Time time = timeRepository.findById(jogadorRequestDTO.getTimeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Time não encontrado"));
            jogador.setTime(time);
        }

        Jogador salvo = repository.save(jogador);

        if (salvo.getTime() != null) {
            HistoricoTime atual = historicoRepository
                    .findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(salvo);

            if (atual == null || !atual.getTime().getId().equals(salvo.getTime().getId())) {
                atualizarHistorico(salvo, salvo.getTime());
            }
        }

        return JogadortoDTOJogador(salvo);
    }

    public List<JogadorResponseDTO> listar() {
        return repository.findAll().stream().map(this::JogadortoDTOJogador).toList(); }

    public JogadorResponseDTO buscarPorId(Long id) {
        Jogador jogador = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jogador não encontrado!"));
        return (JogadortoDTOJogador(jogador));
    }

    public Page<JogadorResponseDTO> buscarComFiltro(Funcao funcao, Long elo,
                                         String nacionalidade, Integer idadeMin,
                                         Integer idadeMax, Long time_id, Pageable pageable) {
            return repository.buscar(funcao, elo, nacionalidade, idadeMin, idadeMax, time_id, pageable).map(this::JogadortoDTOJogador);
    }

    @Transactional
    public void deletar(Long id) {
        Jogador jogador = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jogador não encontrado!"));
        repository.delete(jogador);
    }

    @Transactional
    public void atualizar(Long id, JogadorUpdateDTO jogadorRequestDTO) {
        Jogador novoJogador = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jogador não encontrado!"));

            if (jogadorRequestDTO.getNickname() != null) {
                novoJogador.setNickname(jogadorRequestDTO.getNickname());
            }
            if (jogadorRequestDTO.getNomeCompleto() != null) {
                novoJogador.setNomeCompleto(jogadorRequestDTO.getNomeCompleto());
            }
            if (jogadorRequestDTO.getNacionalidade() != null) {
                novoJogador.setNacionalidade(jogadorRequestDTO.getNacionalidade());
            }
            if (jogadorRequestDTO.getIdade() != null) {
                novoJogador.setIdade(jogadorRequestDTO.getIdade());
            }
            if (jogadorRequestDTO.getHltvUrl() != null) {
                novoJogador.setHltvUrl(jogadorRequestDTO.getHltvUrl());
            }

            Long timeAtualId;
            if (novoJogador.getTime() != null) {
                timeAtualId = novoJogador.getTime().getId();
            } else {
                timeAtualId = null;
            }

            if (jogadorRequestDTO.getTimeId() != null) {
                Long novoTimeId = jogadorRequestDTO.getTimeId();

                if (!Objects.equals(novoTimeId, timeAtualId)) {
                    Time novoTime = timeRepository.findById(novoTimeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Time não encontrado"));

                    novoJogador.setTime(novoTime);
                    atualizarHistorico(novoJogador, novoTime);
                }
            }
            else {
                if (timeAtualId != null) {
                    novoJogador.setTime(null);
                    atualizarHistorico(novoJogador, null);
                }
            }


                if (novoJogador.getPerfilCompetitivo() == null) {
                    PerfilCompetitivo perfil = new PerfilCompetitivo();
                    perfil.setJogador(novoJogador);
                    novoJogador.setPerfilCompetitivo(perfil);
                }
                if (jogadorRequestDTO.getFuncao() != null) {
                    novoJogador.getPerfilCompetitivo()
                            .setFuncao(jogadorRequestDTO.getFuncao());
                }
                if (jogadorRequestDTO.getElo() != null) {
                    novoJogador.getPerfilCompetitivo()
                            .setElo(jogadorRequestDTO.getElo());
                }


            repository.save(novoJogador);
        }

    private void atualizarHistorico(Jogador jogador, Time novoTime) {

        HistoricoTime historicoAtual =
                historicoRepository.findTopByJogadorAndDataSaidaIsNullOrderByDataEntradaDesc(jogador);

        if (historicoAtual == null) {
            if (novoTime != null) {
                HistoricoTime novo = new HistoricoTime();
                novo.setJogador(jogador);
                novo.setTime(novoTime);
                novo.setDataEntrada(LocalDate.now());
                historicoRepository.save(novo);
            }
            return;
        }

        if (novoTime == null) {
            historicoAtual.setDataSaida(LocalDate.now());
            return;
        }

        if (!historicoAtual.getTime().getId().equals(novoTime.getId())) {
            historicoAtual.setDataSaida(LocalDate.now());

            HistoricoTime novo = new HistoricoTime();
            novo.setJogador(jogador);
            novo.setTime(novoTime);
            novo.setDataEntrada(LocalDate.now());
            historicoRepository.save(novo);
        }
    }

    private Jogador JogadorDTOtoJogador(JogadorRequestDTO jogadorDTO) {
        Jogador jogador = new Jogador();

        jogador.setIdade(jogadorDTO.getIdade());
        jogador.setNickname(jogadorDTO.getNickname());
        jogador.setNacionalidade(jogadorDTO.getNacionalidade());
        jogador.setHltvUrl(jogadorDTO.getHltvUrl());
        jogador.setNomeCompleto(jogadorDTO.getNomeCompleto());

        PerfilCompetitivo perfil = new PerfilCompetitivo();
        perfil.setJogador(jogador);
        jogador.setPerfilCompetitivo(perfil);
        jogador.getPerfilCompetitivo().setElo(jogadorDTO.getElo());
        jogador.getPerfilCompetitivo().setFuncao(jogadorDTO.getFuncao());

        return jogador;
    }

    private JogadorResponseDTO JogadortoDTOJogador(Jogador jogador) {
        JogadorResponseDTO jogadorResponseDTO = new JogadorResponseDTO();

        if (jogador.getPerfilCompetitivo() != null) {
            jogadorResponseDTO.setElo(jogador.getPerfilCompetitivo().getElo());
            jogadorResponseDTO.setFuncao(jogador.getPerfilCompetitivo().getFuncao());
        }
        jogadorResponseDTO.setId(jogador.getId());
        jogadorResponseDTO.setNacionalidade(jogador.getNacionalidade());
        jogadorResponseDTO.setNickname(jogador.getNickname());
        jogadorResponseDTO.setIdade(jogador.getIdade());
        jogadorResponseDTO.setNomeCompleto(jogador.getNomeCompleto());
        jogadorResponseDTO.setHltvUrl(jogador.getHltvUrl());
        if (jogador.getTime() != null) {
            jogadorResponseDTO.setNomeTime(jogador.getTime().getNomeTime());
        }
        return  jogadorResponseDTO;
    }
}