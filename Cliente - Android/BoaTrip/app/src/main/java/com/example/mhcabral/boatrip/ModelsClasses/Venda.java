package com.example.mhcabral.boatrip.ModelsClasses;

import java.sql.Date;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Venda {
    private int id;
    private Date data;
    private float valor;
    private String cartao_numero;
    private String validade_cartao;
    private Viagem viagem;
    private Avaliacao avaliacao;
    private Venda_Status estado_venda;
    private Usuario usuario;

    public Venda(int id, Date data, float valor, String validade_cartao, String cartao_numero, Viagem viagem, Avaliacao avaliacao, Venda_Status estado_venda, Usuario usuario) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.validade_cartao = validade_cartao;
        this.cartao_numero = cartao_numero;
        this.viagem = viagem;
        this.avaliacao = avaliacao;
        this.estado_venda = estado_venda;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCartao_numero() {
        return cartao_numero;
    }

    public void setCartao_numero(String cartao_numero) {
        this.cartao_numero = cartao_numero;
    }

    public String getValidade_cartao() {
        return validade_cartao;
    }

    public void setValidade_cartao(String validade_cartao) {
        this.validade_cartao = validade_cartao;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Venda_Status getEstado_venda() {
        return estado_venda;
    }

    public void setEstado_venda(Venda_Status estado_venda) {
        this.estado_venda = estado_venda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
