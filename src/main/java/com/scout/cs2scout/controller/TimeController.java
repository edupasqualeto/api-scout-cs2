package com.scout.cs2scout.controller;

import com.scout.cs2scout.model.Time;
import com.scout.cs2scout.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {
    @Autowired
    private TimeService service;

    @PostMapping
    public Time criar(@RequestBody Time time) {
        return service.salvar(time);
    }

    @GetMapping
    public List<Time> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Time buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.deletar(id); }

}