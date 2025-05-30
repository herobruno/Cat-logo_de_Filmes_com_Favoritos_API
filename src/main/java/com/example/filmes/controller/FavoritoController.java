package com.example.filmes.controller;

import com.example.filmes.model.Usuario;
import com.example.filmes.service.FavoritoService;
import com.example.filmes.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/{filmeId}")
    public ResponseEntity<Void> marcar(@PathVariable String filmeId) {
        String userId = getUserIdFromContext();
        favoritoService.marcarFavorito(userId, filmeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{filmeId}")
    public ResponseEntity<Void> desmarcar(@PathVariable String filmeId) {
        String userId = getUserIdFromContext();
        favoritoService.desmarcarFavorito(userId, filmeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<String>> listar() {
        String userId = getUserIdFromContext();
        return ResponseEntity.ok(favoritoService.listarFavoritos(userId));
    }

    @GetMapping("/{filmeId}")
    public ResponseEntity<Boolean> verificar(@PathVariable String filmeId) {
        String userId = getUserIdFromContext();
        return ResponseEntity.ok(favoritoService.isFavorito(userId, filmeId));
    }

    private String getUserIdFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }
        return authentication.getName(); // Retorna o email do usuário
    }
}