package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 25/07/15.
 */
public class PassagemTipo {
    private int id;
    private String nome;

    public PassagemTipo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
