package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 25/07/15.
 */
public class Profile {
    private int id;
    private String first_name;
    private String last_name;
    private String CPF;
    private String RG;
    private String endereco;
    private String cep;
    private String telefone;
    private String birthdate;
    private String genero;

    public Profile(int id, String first_name, String last_name, String CPF, String RG, String endereco, String cep, String telefone, String birthdate, String genero) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.CPF = CPF;
        this.RG = RG;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
        this.birthdate = birthdate;
        this.genero = genero;
    }

    public Profile(int id){
        this.id = id;
        this.first_name = null;
        this.last_name = null;
        this.CPF = null;
        this.RG = null;
        this.endereco = null;
        this.cep = null;
        this.telefone = null;
        this.birthdate = null;
        this.genero = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cep='" + cep + '\'' +
                ", telefone='" + telefone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
