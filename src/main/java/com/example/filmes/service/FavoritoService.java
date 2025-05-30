package com.example.filmes.service;

import com.example.filmes.model.Favorito;
import com.example.filmes.repository.FavoritoRepository;
import com.example.filmes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void marcarFavorito(String userEmail, String filmeId) {
        if (!usuarioRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        boolean jaExiste = favoritoRepository.findByUserIdAndFilmeId(userEmail, filmeId).isPresent();
        if (!jaExiste) {
            Favorito fav = new Favorito();
            fav.setUserId(userEmail);
            fav.setFilmeId(filmeId);
            favoritoRepository.save(fav);
        }
    }

    public void desmarcarFavorito(String userEmail, String filmeId) {
        if (!usuarioRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        favoritoRepository.deleteByUserIdAndFilmeId(userEmail, filmeId);
    }

    public List<String> listarFavoritos(String userEmail) {
        if (!usuarioRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return favoritoRepository.findByUserId(userEmail)
                .stream()
                .map(Favorito::getFilmeId)
                .toList();
    }

    public boolean isFavorito(String userEmail, String filmeId) {
        if (!usuarioRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return favoritoRepository.findByUserIdAndFilmeId(userEmail, filmeId).isPresent();
    }
}