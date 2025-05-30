package com.example.filmes.controller;

import com.example.filmes.model.Avaliacao;
import com.example.filmes.model.Usuario;
import com.example.filmes.service.AvaliacaoService;
import com.example.filmes.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{filmeId}")
    public List<Avaliacao> listarAvaliacoes(@PathVariable String filmeId) {
        return avaliacaoService.listarPorFilme(filmeId);
    }

    @PostMapping("/{filmeId}")
    public ResponseEntity<Avaliacao> adicionarAvaliacao(
            @PathVariable String filmeId,
            @Valid @RequestBody Avaliacao avaliacao) {
        
        String userEmail = getEmailFromContext();
        Usuario usuario = usuarioService.buscarPorEmail(userEmail);
        
        avaliacao.setAutor(usuario.getNome());
        Avaliacao nova = avaliacaoService.adicionarAvaliacao(filmeId, avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    private String getEmailFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }
        return authentication.getName();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            if (fieldName.equals("nota")) {
                errorMessage = "A nota deve estar entre 1 e 5";
            }
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            if (fieldName.contains("nota")) {
                errorMessage = "A nota deve estar entre 1 e 5";
            }
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}