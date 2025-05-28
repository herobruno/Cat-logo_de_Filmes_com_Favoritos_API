package com.example.filmes.controller;

import com.example.filmes.model.Avaliacao;
import com.example.filmes.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/{filmeId}")
    public List<Avaliacao> listarAvaliacoes(@PathVariable String filmeId) {
        return avaliacaoService.listarPorFilme(filmeId);
    }

    @PostMapping("/{filmeId}")
    public ResponseEntity<Avaliacao> adicionarAvaliacao(
            @PathVariable String filmeId,
            @RequestBody Avaliacao avaliacao) {
        Avaliacao nova = avaliacaoService.adicionarAvaliacao(filmeId, avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }
}