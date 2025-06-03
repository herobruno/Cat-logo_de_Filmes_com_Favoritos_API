package com.example.filmes.controller;

import com.example.filmes.model.Filme;
import com.example.filmes.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme) {
        Filme salvo = filmeService.salvar(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable String id, @RequestBody Filme filmeAtualizado) {
        if (!filmeService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        filmeAtualizado.setId(id);
        Filme filmeSalvo = filmeService.salvar(filmeAtualizado);
        return ResponseEntity.ok(filmeSalvo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarFilme(@PathVariable String id) {
        if (!filmeService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}