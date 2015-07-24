package com.example.mhcabral.boatrip.ModelsClasses;

import android.graphics.drawable.Drawable;

import java.sql.Date;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Usuario {
    private int id;
    private String nome;
    private String auth_key;
    private String password_hash;
    private String password_reset_token;
    private String email;
    private Usuario_funcao funcao;
    private Usuario_status status;
    private Date data_criacao;
    private Drawable icon;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario_funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Usuario_funcao funcao) {
        this.funcao = funcao;
    }

    public Usuario_status getStatus() {
        return status;
    }

    public void setStatus(Usuario_status status) {
        this.status = status;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
