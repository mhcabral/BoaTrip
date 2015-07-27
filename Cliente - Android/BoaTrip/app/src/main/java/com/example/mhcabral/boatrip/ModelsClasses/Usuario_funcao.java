package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Usuario_funcao {
    private int id;
    private String nome;
    private int valor;

    public Usuario_funcao() {
        this.id = 0;
        this.nome = null;
        this.valor = 0;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
