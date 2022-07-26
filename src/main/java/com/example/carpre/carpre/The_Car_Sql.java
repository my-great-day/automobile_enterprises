package com.example.carpre.carpre;

public class The_Car_Sql {
    int id, vin;

    String tip_auto;
    String marka;
    String god_vipusk;
    String gos_nomer;
    String nomer_dvigatel;
    String nomer_kuzov;
    String garantiy;
    String stoimost;
    String marka_topliva;

    public The_Car_Sql(int id, String tip_auto, String marka, String god_vipusk, int vin, String gos_nomer, String nomer_dvigatel, String nomer_kuzov, String garantiy, String stoimost, String marka_topliva) {
        this.id = id;
        this.vin = vin;
        this.tip_auto = tip_auto;
        this.marka = marka;
        this.god_vipusk = god_vipusk;
        this.gos_nomer = gos_nomer;
        this.nomer_dvigatel = nomer_dvigatel;
        this.nomer_kuzov = nomer_kuzov;
        this.garantiy = garantiy;
        this.stoimost = stoimost;
        this.marka_topliva = marka_topliva;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getTip_auto() {
        return tip_auto;
    }

    public void setTip_auto(String tip_auto) {
        this.tip_auto = tip_auto;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getGod_vipusk() {
        return god_vipusk;
    }

    public void setGod_vipusk(String god_vipusk) {
        this.god_vipusk = god_vipusk;
    }

    public String getGos_nomer() {
        return gos_nomer;
    }

    public void setGos_nomer(String gos_nomer) {
        this.gos_nomer = gos_nomer;
    }

    public String getNomer_dvigatel() {
        return nomer_dvigatel;
    }

    public void setNomer_dvigatel(String nomer_dvigatel) {
        this.nomer_dvigatel = nomer_dvigatel;
    }

    public String getNomer_kuzov() {
        return nomer_kuzov;
    }

    public void setNomer_kuzov(String nomer_kuzov) {
        this.nomer_kuzov = nomer_kuzov;
    }

    public String getGarantiy() {
        return garantiy;
    }

    public void setGarantiy(String garantiy) {
        this.garantiy = garantiy;
    }

    public String getStoimost() {
        return stoimost;
    }

    public void setStoimost(String stoimost) {
        this.stoimost = stoimost;
    }

    public String getMarka_topliva() {
        return marka_topliva;
    }

    public void setMarka_topliva(String marka_topliva) {
        this.marka_topliva = marka_topliva;
    }
}



//        id
//        tip_auto
//        marka
//        god_vipusk,
//        vin
//        gos_nomer
//        nomer_dvigatel
//        nomer_kuzov
//        garantiy
//        stoimost
//        marka_topliva
