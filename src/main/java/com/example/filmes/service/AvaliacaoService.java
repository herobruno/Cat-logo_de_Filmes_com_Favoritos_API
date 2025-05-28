package com.example.filmes.service;

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
        return avaliacaoRepository.findByFilmeId(filmeId);
    }

    public Avaliacao adicionarAvaliacao(String filmeId, Avaliacao avaliacao) {
        if (!filmeRepository.existsById(filmeId)) {
            throw new RuntimeException("Filme não encontrado");
        }

        avaliacao.setFilmeId(filmeId);
        return avaliacaoRepository.save(avaliacao);
    }
}