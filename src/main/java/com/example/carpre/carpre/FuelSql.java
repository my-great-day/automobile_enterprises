package com.example.carpre.carpre;

public class FuelSql {
    int id;
    String nomer_cheka, toplivo, chek;

    public FuelSql(int id, String nomer_cheka, String toplivo, String chek) {
        this.id = id;
        this.nomer_cheka = nomer_cheka;
        this.toplivo = toplivo;
        this.chek = chek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomer_cheka() {
        return nomer_cheka;
    }

    public void setNomer_cheka(String nomer_cheka) {
        this.nomer_cheka = nomer_cheka;
    }

    public String getToplivo() {
        return toplivo;
    }

    public void setToplivo(String toplivo) {
        this.toplivo = toplivo;
    }

    public String getChek() {
        return chek;
    }

    public void setChek(String chek) {
        this.chek = chek;
    }
}
