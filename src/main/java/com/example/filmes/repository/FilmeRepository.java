package com.example.filmes.repository;

import com.example.filmes.model.Filme;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface FilmeRepository extends MongoRepository<Filme, String> {

    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    List<Filme> findByGenerosContainingIgnoreCase(String genero);
}