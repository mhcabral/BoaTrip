package com.example.mhcabral.boatrip.ModelsClasses;

import java.util.List;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Barco {
    private int id;
    private String nome;
    private String descricao;
    private int numero_tripulantes;
    private List<Barco_foto> fotos;

    public Barco(int id, String nome, String descricao, List<Barco_foto> fotos, int numero_tripulantes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fotos = fotos;
        this.numero_tripulantes = numero_tripulantes;
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

    public int getNumero_tripulantes() {
        return numero_tripulantes;
    }

    public void setNumero_tripulantes(int numero_tripulantes) {
        this.numero_tripulantes = numero_tripulantes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Barco_foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Barco_foto> fotos) {
        this.fotos = fotos;
    }
}
