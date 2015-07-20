package com.example.mhcabral.boatrip.Controllers;

import android.content.Context;

import com.example.mhcabral.boatrip.ModelsClasses.Barco;
import com.example.mhcabral.boatrip.ModelsClasses.Localidade;
import com.example.mhcabral.boatrip.ModelsClasses.Usuario;
import com.example.mhcabral.boatrip.ModelsClasses.Venda;
import com.example.mhcabral.boatrip.ModelsClasses.Viagem;

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
    private static int idbusca_origem;
    private static String dbusca_destino;
    private static int idbusca_destino;
    private static String mesAno;
    private static String erroBusca;
    private static List<Viagem> listviagens;
    private static List<Barco> listbarcos;
    private static Localidade origemBuscado;
    private static Localidade destinoBuscado;

    public static void initInstance(Context current) {
        if (instance == null) {
            // Create the instance
            instance = new Stub2();
            listlocalidade = new ArrayList<Localidade>();
            listvendas = new ArrayList<Venda>();
            user = new Usuario("Novo Usuario","Adicionar Usuario",current);
            dbusca_origem = null;
            dbusca_destino = null;
            idbusca_origem = 0;
            idbusca_destino = 0;
            erroBusca = null;
            mesAno = null;
            listviagens = new ArrayList<Viagem>();
            listbarcos = new ArrayList<Barco>();
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

    public static int getIdbusca_origem() {
        return idbusca_origem;
    }

    public static void setIdbusca_origem(int idbusca_origem) {
        Stub2.idbusca_origem = idbusca_origem;
    }

    public static int getIdbusca_destino() {
        return idbusca_destino;
    }

    public static void setIdbusca_destino(int idbusca_destino) {
        Stub2.idbusca_destino = idbusca_destino;
    }

    public static String getMesAno() {
        return mesAno;
    }

    public static void setMesAno(String mesAno) {
        Stub2.mesAno = mesAno;
    }

    public static List<Barco> getListbarcos() {
        return listbarcos;
    }

    public static void setListbarcos(List<Barco> listbarcos) {
        Stub2.listbarcos = listbarcos;
    }

    public static void addListbarcos(Barco novoBarco){
        Stub2.listbarcos.add(novoBarco);
    }

    public static List<Viagem> getListviagens() {
        return listviagens;
    }

    public static void setListviagens(List<Viagem> listviagens) {
        Stub2.listviagens = listviagens;
    }

    public static void addListviagens(Viagem novaViagem){
        Stub2.listviagens.add(novaViagem);
    }

    public static Localidade getOrigemBuscado() {
        return origemBuscado;
    }

    public static void setOrigemBuscado(Localidade origemBuscado) {
        Stub2.origemBuscado = origemBuscado;
    }

    public static Localidade getDestinoBuscado() {
        return destinoBuscado;
    }

    public static void setDestinoBuscado(Localidade destinoBuscado) {
        Stub2.destinoBuscado = destinoBuscado;
    }

    public static String getErroBusca() {
        return erroBusca;
    }

    public static void setErroBusca(String erroBusca) {
        Stub2.erroBusca = erroBusca;
    }
}