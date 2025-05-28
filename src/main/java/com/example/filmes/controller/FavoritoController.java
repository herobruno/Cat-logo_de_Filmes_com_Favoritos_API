package com.example.filmes.controller;

import com.example.filmes.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping("/{userId}/{filmeId}")
    public ResponseEntity<Void> marcar(@PathVariable String userId, @PathVariable String filmeId) {
        favoritoService.marcarFavorito(userId, filmeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{filmeId}")
    public ResponseEntity<Void> desmarcar(@PathVariable String userId, @PathVariable String filmeId) {
        favoritoService.desmarcarFavorito(userId, filmeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<String>> listar(@PathVariable String userId) {
        return ResponseEntity.ok(favoritoService.listarFavoritos(userId));
    }

    @GetMapping("/{userId}/{filmeId}")
    public ResponseEntity<Boolean> verificar(@PathVariable String userId, @PathVariable String filmeId) {
        return ResponseEntity.ok(favoritoService.isFavorito(userId, filmeId));
    }
}