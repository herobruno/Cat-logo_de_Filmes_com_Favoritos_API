package com.example.filmes.service;

import com.example.filmes.exception.FilmeSemAvaliacaoException;
import com.example.filmes.model.Avaliacao;
import com.example.filmes.repository.AvaliacaoRepository;
import com.example.filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Avaliacao> listarPorFilme(String filmeId) {
        if (!filmeRepository.existsById(filmeId)) {
            throw new FilmeSemAvaliacaoException("Filme não encontrado");
        }

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByFilmeId(filmeId);
        if (avaliacoes.isEmpty()) {
            throw new FilmeSemAvaliacaoException("O filme não possui avaliações");
        }

        return avaliacoes;
    }

    public Avaliacao adicionarAvaliacao(String filmeId, Avaliacao avaliacao) {
        if (!filmeRepository.existsById(filmeId)) {
            throw new FilmeSemAvaliacaoException("Filme não encontrado");
        }

        avaliacao.setFilmeId(filmeId);
        return avaliacaoRepository.save(avaliacao);
    }
}