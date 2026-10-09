package com.scout.cs2scout.contr Se o seu sistema é (e sempre será) um CRUD, provavelmente não há necessidade de usar DDD.oller;

import com.scout.cs2scout.dto.JogadorRequestDTO;
import com.scout.cs2scout.dto.JogadorResponseDTO;
import com.scout.cs2scout.dto.JogadorUpdateDTO;
import com.scout.cs2scout.model.*;
import com.scout.cs2scout.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    @Autowired
    private JogadorService service;

    @PostMapping
    public ResponseEntity<JogadorResponseDTO> criar(@Valid @RequestBody JogadorRequestDTO jogadorRequestDTO) {
        JogadorResponseDTO jogadorResponseDTO = service.salvar(jogadorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<JogadorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<JogadorResponseDTO>> buscarComFiltro(@RequestParam(required = false) Funcao funcao,
                                                                    @RequestParam(required = false) Long elo,
                                                                    @RequestParam(required = false) String nacionalidade,
                                                                    @RequestParam(required = false) Integer idadeMin,
                                                                    @RequestParam(required = false) Integer idadeMax,
                                                                    @RequestParam(required = false) Long time_id,
                                                                    @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.buscarComFiltro(funcao, elo, nacionalidade, idadeMin, idadeMax, time_id, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody JogadorUpdateDTO jogadorUpdateDTO) {
        service.atualizar(id, jogadorUpdateDTO);
        return ResponseEntity.noContent().build();
        }
    }