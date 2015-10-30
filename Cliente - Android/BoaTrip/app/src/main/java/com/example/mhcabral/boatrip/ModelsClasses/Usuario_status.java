package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Usuario_status {
    private int id;
    private String nome;
    private String valor;

    public Usuario_status() {
        this.id = 0;
        this.nome = null;
        this.valor = null;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Usuario_status{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
