package com.example.mhcabral.boatrip.Controllers;

import android.content.Context;

import com.example.mhcabral.boatrip.ModelsClasses.Barco;
import com.example.mhcabral.boatrip.ModelsClasses.Genero;
import com.example.mhcabral.boatrip.ModelsClasses.Localidade;
import com.example.mhcabral.boatrip.ModelsClasses.PassagemTipo;
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
    private static Usuario user;
    private static String dbusca_origem;
    private static String dbusca_destino;
    private static String mesAno;
    private static int idbusca_origem;
    private static int idbusca_destino;
    private static List<Localidade> listlocalidade;
    private static List<Viagem> listviagens;
    private static List<Barco> listbarcos;
    private static List<Venda> listvendas;
    private static List<Viagem> listpromocoes;
    private static List<PassagemTipo> listpassagemtipo;
    private static List<Genero> listgenero;
    private static Localidade origemBuscado;
    private static Localidade destinoBuscado;
    private static String prefix_url;
    private static boolean loadedBarcos;

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
            mesAno = null;
            listviagens = new ArrayList<Viagem>();
            listbarcos = new ArrayList<Barco>();
            prefix_url = "http://boatrip.microben.com.br";
            listpromocoes = new ArrayList<Viagem>();
            listpassagemtipo = new ArrayList<PassagemTipo>();
            listgenero = new ArrayList<Genero>();
            loadedBarcos = false;
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

    public static String getPrefix_url() {
        return prefix_url;
    }

    public static void setPrefix_url(String prefix_url) {
        Stub2.prefix_url = prefix_url;
    }

    public static List<Viagem> getListpromocoes() {
        return listpromocoes;
    }

    public static void setListpromocoes(List<Viagem> listpromocoes) {
        Stub2.listpromocoes = listpromocoes;
    }

    public static void addListpromocoes(Viagem novaPromocao){
        Stub2.listpromocoes.add(novaPromocao);
    }

    public static List<PassagemTipo> getListpassagemtipo() {
        return listpassagemtipo;
    }

    public static void setListpassagemtipo(List<PassagemTipo> listpassagemtipo) {
        Stub2.listpassagemtipo = listpassagemtipo;
    }

    public static List<Genero> getListgenero() {
        return listgenero;
    }

    public static void setListgenero(List<Genero> listgenero) {
        Stub2.listgenero = listgenero;
    }

    public static boolean isLoadedBarcos() {
        return loadedBarcos;
    }

    public static void setLoadedBarcos(boolean loadedBarcos) {
        Stub2.loadedBarcos = loadedBarcos;
    }
}
