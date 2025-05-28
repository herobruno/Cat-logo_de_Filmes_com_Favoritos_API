package com.example.filmes.service;

import com.example.filmes.model.Filme;
import com.example.filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Optional<Filme> buscarPorId(String id) {
        return filmeRepository.findById(id);
    }

    public List<Filme> buscarPorTitulo(String titulo) {
        return filmeRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Filme> filtrarPorGenero(String genero) {
        return filmeRepository.findByGenerosContainingIgnoreCase(genero);
    }

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    public void deletar(String id) {
        filmeRepository.deleteById(id);
    }
}