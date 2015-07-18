package com.example.mhcabral.boatrip.Controllers;

import android.content.Context;

import com.example.mhcabral.boatrip.ModelsClasses.Localidade;
import com.example.mhcabral.boatrip.ModelsClasses.Usuario;
import com.example.mhcabral.boatrip.ModelsClasses.Venda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhcabral on 14/07/15.
 */
public class Stub2 {
    private static Stub2 instance;
    private static List<Localidade> listlocalidade;
    private static Usuario user;
    private static List<Venda> listvendas;
    private static String dbusca_origem;
    private static String dbusca_destino;

    public static void initInstance(Context current) {
        if (instance == null) {
            // Create the instance
            instance = new Stub2();
            listlocalidade = new ArrayList<Localidade>();
            listvendas = new ArrayList<Venda>();
            user = new Usuario("Novo Usuario","Adicionar Usuario",current);
            dbusca_origem = null;
            dbusca_destino = null;
        }
    }

    public static Stub2 getInstance() {
        return instance;
    }

    public static List<Localidade> getListlocalidade() {
        return listlocalidade;
    }

    public static void setListlocalidade(List<Localidade> listlocalidade) {
        Stub2.listlocalidade = listlocalidade;
    }

    public static void addListlocalidade(Localidade novalocalidade){
        Stub2.listlocalidade.add(novalocalidade);
    }

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        Stub2.user = user;
    }

    public static List<Venda> getListvendas() {
        return listvendas;
    }

    public static void setListvendas(List<Venda> listvendas) {
        Stub2.listvendas = listvendas;
    }

    public static void addListvendas(Venda novavenda){
        Stub2.listvendas.add(novavenda);
    }

    public static String getDbusca_origem() {
        return dbusca_origem;
    }

    public static void setDbusca_origem(String dbusca_origem) {
        Stub2.dbusca_origem = dbusca_origem;
    }

    public static String getDbusca_destino() {
        return dbusca_destino;
    }

    public static void setDbusca_destino(String dbusca_destino) {
        Stub2.dbusca_destino = dbusca_destino;
    }
}
