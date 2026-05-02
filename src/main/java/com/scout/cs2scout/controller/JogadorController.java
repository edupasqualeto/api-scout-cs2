package com.scout.cs2scout.controller;

import com.scout.cs2scout.dto.JogadorRequestDTO;
import com.scout.cs2scout.dto.JogadorResponseDTO;
import com.scout.cs2scout.model.*;
import com.scout.cs2scout.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    @Autowired
    private JogadorService service;

    @PostMapping
    public JogadorResponseDTO criar(@RequestBody JogadorRequestDTO jogadorRequestDTO) {
        return service.salvar(jogadorRequestDTO);
    }

    @GetMapping
    public List<JogadorResponseDTO> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public JogadorResponseDTO buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/buscar")
    public Page<JogadorResponseDTO> buscarComFiltro(@RequestParam(required = false) Funcao funcao,
                                         @RequestParam(required = false) Long elo,
                                         @RequestParam(required = false) String nacionalidade,
                                         @RequestParam(required = false) Integer idadeMin,
                                         @RequestParam(required = false) Integer idadeMax,
                                         @RequestParam(required = false) Long time_id,
                                         @PageableDefault(size = 10) Pageable pageable)
    { return service.buscarComFiltro(funcao, elo, nacionalidade, idadeMin, idadeMax, time_id, pageable); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.deletar(id); }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody JogadorRequestDTO jogadorRequestDTO) {
        service.atualizar(id, jogadorRequestDTO);
    }

}