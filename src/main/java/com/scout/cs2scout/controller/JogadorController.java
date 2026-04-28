package com.scout.cs2scout.controller;

import com.scout.cs2scout.model.Funcao;
import com.scout.cs2scout.model.Jogador;
import com.scout.cs2scout.service.JogadorService;
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

    @GetMapping("/buscar")
    public List<Jogador> buscarComFiltro(@RequestParam Funcao funcao,
                                         @RequestParam(required = false) Long elo,
                                         @RequestParam(required = false) String nacionalidade)
    { return service.buscar(funcao, elo, nacionalidade); }

    @GetMapping("/{id}")
    public Jogador buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody Jogador jogador) {
        service.atualizar(id, jogador);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.remover(id); }

}