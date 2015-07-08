package com.example.mhcabral.boatrip.ModelsClasses;

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

    public Usuario(int id, String nome, String auth_key, String password_hash, String password_reset_token, String email, Usuario_funcao funcao, Usuario_status status, Date data_criacao) {
        this.id = id;
        this.nome = nome;
        this.auth_key = auth_key;
        this.password_hash = password_hash;
        this.password_reset_token = password_reset_token;
        this.email = email;
        this.funcao = funcao;
        this.status = status;
        this.data_criacao = data_criacao;
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
}
