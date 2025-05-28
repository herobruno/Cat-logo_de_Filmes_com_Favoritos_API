package com.example.filmes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Objects;

@Document(collection = "filmes")
public class Filme {

    @Id
    private String id;
    private String titulo;
    private String imagemUrl;
    private Integer ano;
    private List<String> generos;
    private String sinopse;

    public Filme() {}

    public Filme(String titulo, String imagemUrl, Integer ano, List<String> generos, String sinopse) {
        this.titulo = titulo;
        this.imagemUrl = imagemUrl;
        this.ano = ano;
        this.generos = generos;
        this.sinopse = sinopse;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public List<String> getGeneros() { return generos; }
    public void setGeneros(List<String> generos) { this.generos = generos; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme)) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", generos=" + generos +
                '}';
    }
}