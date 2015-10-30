package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Passagem {
    private int id;
    private String tpassagem;
    private int quantidade;
    private float valor;
    private float valor_desconto;

    public Passagem(int id, String tpassagem, int quantidade,float valor,float valor_desconto) {
        this.id = id;
        this.tpassagem = tpassagem;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valor_desconto = valor_desconto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTpassagem() {
        return tpassagem;
    }

    public void setTpassagem(String tpassagem) {
        this.tpassagem = tpassagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    @Override
    public String toString() {
        return "Passagem{" +
                "id=" + id +
                ", tpassagem='" + tpassagem + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", valor_desconto=" + valor_desconto +
                '}';
    }
}
