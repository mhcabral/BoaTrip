package com.example.mhcabral.boatrip.ModelsClasses;


/**
 * Created by mhcabral on 07/07/15.
 */
public class Viagem {
    private int id;
    private String data_saida;
    private String data_chegada;
    private float valor;
    private float valor_desconto;
    private String percurso;
    private Localidade origem;
    private Localidade destino;
    private Barco barco;

    public Viagem(int id, long data_saida, long data_chegada, float valor, float valor_desconto, String percurso, Localidade origem, Localidade destino, Barco barco) {
        this.id = id;
        this.data_saida = String.valueOf(data_saida);
        this.data_chegada = String.valueOf(data_chegada);
        this.valor = valor;
        this.valor_desconto = valor_desconto;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getValor_desconto() {
        return valor_desconto;
    }

    public void setValor_desconto(float valor_desconto) {
        this.valor_desconto = valor_desconto;
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
}
