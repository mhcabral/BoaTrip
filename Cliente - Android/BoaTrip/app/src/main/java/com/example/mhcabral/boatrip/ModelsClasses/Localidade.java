package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Localidade {
    private int id;
    private String nome;
    private Uf uf;
    private String localidade_url;

    public Localidade(int id,String nome, Uf uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.localidade_url = "";
    }

    public Localidade(int id, String nome, Uf uf, String localidade_url) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.localidade_url = localidade_url;
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

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public String getLocalidade_url() {
        return localidade_url;
    }

    public void setLocalidade_url(String localidade_url) {
        this.localidade_url = localidade_url;
    }

    @Override
    public String toString() {
        return "Localidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf=" + uf.toString() +
                ", localidade_url='" + localidade_url + '\'' +
                '}';
    }
}
