package com.example.filmes.controller;

import com.example.filmes.model.Filme;
import com.example.filmes.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarTodos() {
        return ResponseEntity.ok(filmeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable String id) {
        return filmeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Filme>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(filmeService.buscarPorTitulo(titulo));
    }

    @GetMapping("/genero")
    public ResponseEntity<List<Filme>> filtrarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(filmeService.filtrarPorGenero(genero));
    }

    @PostMapping
    public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme) {
        Filme salvo = filmeService.salvar(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable String id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}