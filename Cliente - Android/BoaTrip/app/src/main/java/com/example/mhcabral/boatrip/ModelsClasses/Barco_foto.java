package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Barco_foto {
    private int id;
    private String foto_url;

    public Barco_foto(int id, String foto_url) {
        this.id = id;
        this.foto_url = foto_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }
}


