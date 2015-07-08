package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Passagem {
    private int id;
    private Viagem viagem;
    private TipoDePassagem tpassagem;
    private int quantidade;
    private int restante;

    public Passagem(int id, Viagem viagem, TipoDePassagem tpassagem, int quantidade, int restante) {
        this.id = id;
        this.viagem = viagem;
        this.tpassagem = tpassagem;
        this.quantidade = quantidade;
        this.restante = restante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public TipoDePassagem getTpassagem() {
        return tpassagem;
    }

    public void setTpassagem(TipoDePassagem tpassagem) {
        this.tpassagem = tpassagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getRestante() {
        return restante;
    }

    public void setRestante(int restante) {
        this.restante = restante;
    }
}
