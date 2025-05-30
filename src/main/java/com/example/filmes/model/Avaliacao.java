package com.example.filmes.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "avaliacoes")
public class Avaliacao {
    @Id
    private String id;

    private String autor; // Nome do usuário que fez a avaliação
    private String comentario;
    
    @Min(1)
    @Max(5)
    private int nota;
    
    private String filmeId;

    public Avaliacao() {}

    public Avaliacao(String autor, String comentario, int nota, String filmeId) {
        this.autor = autor;
        this.comentario = comentario;
        this.nota = nota;
        this.filmeId = filmeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }
}