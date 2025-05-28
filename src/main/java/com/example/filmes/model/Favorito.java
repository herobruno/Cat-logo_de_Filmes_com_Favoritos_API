package com.example.filmes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favoritos")
public class Favorito {
    @Id
    private String id;

    private String userId;

    public Favorito(String id, String userId, String filmeId) {
        this.id = id;
        this.userId = userId;
        this.filmeId = filmeId;
    }

    private String filmeId;

    public Favorito() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }
}