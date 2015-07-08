package com.example.mhcabral.boatrip.ModelsClasses;

/**
 * Created by mhcabral on 07/07/15.
 */
public class Avaliacao {
    private int id;
    private int estrela1_qt;
    private int estrela2_qt;
    private int estrela3_qt;
    private String mensagem;

    public Avaliacao(int id, int estrela1_qt, int estrela2_qt, int estrela3_qt, String mensagem) {
        this.id = id;
        this.estrela1_qt = estrela1_qt;
        this.estrela2_qt = estrela2_qt;
        this.estrela3_qt = estrela3_qt;
        this.mensagem = mensagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstrela1_qt() {
        return estrela1_qt;
    }

    public void setEstrela1_qt(int estrela1_qt) {
        this.estrela1_qt = estrela1_qt;
    }

    public int getEstrela2_qt() {
        return estrela2_qt;
    }

    public void setEstrela2_qt(int estrela2_qt) {
        this.estrela2_qt = estrela2_qt;
    }

    public int getEstrela3_qt() {
        return estrela3_qt;
    }

    public void setEstrela3_qt(int estrela3_qt) {
        this.estrela3_qt = estrela3_qt;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
