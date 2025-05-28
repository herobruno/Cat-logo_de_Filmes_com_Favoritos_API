package com.example.filmes.service;

import com.example.filmes.model.Favorito;
import com.example.filmes.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    public void marcarFavorito(String userId, String filmeId) {
        boolean jaExiste = favoritoRepository.findByUserIdAndFilmeId(userId, filmeId).isPresent();
        if (!jaExiste) {
            Favorito fav = new Favorito();
            fav.setUserId(userId);
            fav.setFilmeId(filmeId);
            favoritoRepository.save(fav);
        }
    }

    public void desmarcarFavorito(String userId, String filmeId) {
        favoritoRepository.deleteByUserIdAndFilmeId(userId, filmeId);
    }

    public List<String> listarFavoritos(String userId) {
        return favoritoRepository.findByUserId(userId)
                .stream()
                .map(Favorito::getFilmeId)
                .toList();
    }

    public boolean isFavorito(String userId, String filmeId) {
        return favoritoRepository.findByUserIdAndFilmeId(userId, filmeId).isPresent();
    }
}