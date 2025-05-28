package com.example.filmes.repository;

import com.example.filmes.model.Favorito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends MongoRepository<Favorito, String> {
    Optional<Favorito> findByUserIdAndFilmeId(String userId, String filmeId);
    List<Favorito> findByUserId(String userId);
    void deleteByUserIdAndFilmeId(String userId, String filmeId);
}