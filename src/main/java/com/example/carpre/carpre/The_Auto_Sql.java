package com.example.carpre.carpre;

public class The_Auto_Sql {
    int id;
    String drive,date_viezd, date_vozv, spid_viezd, spid_vozv, topl_viezd, topl_vozv, mesto, rastoyanie, massa_gruza;

    public The_Auto_Sql(int id,String drive, String date_viezd, String date_vozv, String spid_viezd, String spid_vozv, String topl_viezd, String topl_vozv, String mesto, String rastoyanie, String massa_gruza) {
        this.id = id;
        this.drive = drive;
        this.date_viezd = date_viezd;
        this.date_vozv = date_vozv;
        this.spid_viezd = spid_viezd;
        this.spid_vozv = spid_vozv;
        this.topl_viezd = topl_viezd;
        this.topl_vozv = topl_vozv;
        this.mesto = mesto;
        this.rastoyanie = rastoyanie;
        this.massa_gruza = massa_gruza;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getDate_viezd() {
        return date_viezd;
    }

    public void setDate_viezd(String date_viezd) {
        this.date_viezd = date_viezd;
    }

    public String getDate_vozv() {
        return date_vozv;
    }

    public void setDate_vozv(String date_vozv) {
        this.date_vozv = date_vozv;
    }

    public String getSpid_viezd() {
        return spid_viezd;
    }

    public void setSpid_viezd(String spid_viezd) {
        this.spid_viezd = spid_viezd;
    }

    public String getSpid_vozv() {
        return spid_vozv;
    }

    public void setSpid_vozv(String spid_vozv) {
        this.spid_vozv = spid_vozv;
    }

    public String getTopl_viezd() {
        return topl_viezd;
    }

    public void setTopl_viezd(String topl_viezd) {
        this.topl_viezd = topl_viezd;
    }

    public String getTopl_vozv() {
        return topl_vozv;
    }

    public void setTopl_vozv(String topl_vozv) {
        this.topl_vozv = topl_vozv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getRastoyanie() {
        return rastoyanie;
    }

    public void setRastoyanie(String rastoyanie) {
        this.rastoyanie = rastoyanie;
    }

    public String getMassa_gruza() {
        return massa_gruza;
    }

    public void setMassa_gruza(String massa_gruza) {
        this.massa_gruza = massa_gruza;
    }

}
