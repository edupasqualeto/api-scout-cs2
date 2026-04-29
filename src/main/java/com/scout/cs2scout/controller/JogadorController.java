package com.scout.cs2scout.controller;

import com.scout.cs2scout.model.*;
import com.scout.cs2scout.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    @Autowired
    private JogadorService service;

    @PostMapping
    public Jogador criar(@RequestBody Jogador jogador) {
        return service.salvar(jogador);
    }

    @GetMapping
    public List<Jogador> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Jogador buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/buscar")
    public List<Jogador> buscarComFiltro(@RequestParam(required = false) Funcao funcao,
                                         @RequestParam(required = false) Long elo,
                                         @RequestParam(required = false) String nacionalidade,
                                         @RequestParam(required = false) Integer idadeMin,
                                         @RequestParam(required = false) Integer idadeMax,
                                         @RequestParam(required = false) Long time_id)
    { return service.buscarComFiltro(funcao, elo, nacionalidade, idadeMin, idadeMax, time_id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.deletar(id); }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody Jogador jogador) {
        service.atualizar(id, jogador);
    }

}