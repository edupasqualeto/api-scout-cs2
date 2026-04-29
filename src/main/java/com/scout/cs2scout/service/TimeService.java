package com.scout.cs2scout.service;

import com.scout.cs2scout.model.*;
import com.scout.cs2scout.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimeService {
    @Autowired
    private TimeRepository repository;

    public Time salvar(Time time) { return repository.save(time); }

    public List<Time> listar() { return repository.findAll(); }

    public Time buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Long id) { repository.deleteById(id); }

}
