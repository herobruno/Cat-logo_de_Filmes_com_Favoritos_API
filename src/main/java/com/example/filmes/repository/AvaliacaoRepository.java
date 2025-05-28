package com.example.filmes.repository;

import com.example.filmes.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvaliacaoRepository extends MongoRepository<Avaliacao, String> {
    List<Avaliacao> findByFilmeId(String filmeId);
}