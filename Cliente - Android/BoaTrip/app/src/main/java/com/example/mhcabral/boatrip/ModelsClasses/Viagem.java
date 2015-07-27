package com.example.mhcabral.boatrip.ModelsClasses;


import java.util.List;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Viagem {
    private int id;
    private String data_saida;
    private String data_chegada;
    private String percurso;
    private Localidade origem;
    private Localidade destino;
    private Barco barco;
    private List<Passagem> listPassagem;

    public Viagem(int id, String data_saida, String data_chegada,List<Passagem> listPassagem, String percurso, Localidade origem, Localidade destino, Barco barco) {
        this.id = id;
        this.data_saida = data_saida;
        this.data_chegada = data_chegada;
        this.listPassagem = listPassagem;
        this.percurso = percurso;
        this.origem = origem;
        this.destino = destino;
        this.barco = barco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public String getData_chegada() {
        return data_chegada;
    }

    public void setData_chegada(String data_chegada) {
        this.data_chegada = data_chegada;
    }

    public String getPercurso() {
        return percurso;
    }

    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

    public Localidade getOrigem() {
        return origem;
    }

    public void setOrigem(Localidade origem) {
        this.origem = origem;
    }

    public Localidade getDestino() {
        return destino;
    }

    public void setDestino(Localidade destino) {
        this.destino = destino;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public List<Passagem> getListPassagem() {
        return listPassagem;
    }

    public void setListPassagem(List<Passagem> listpassagem) {
        this.listPassagem = listPassagem;
    }

    public void addListPassagem(Passagem novaPassagem){
        this.listPassagem.add(novaPassagem);
    }
}
