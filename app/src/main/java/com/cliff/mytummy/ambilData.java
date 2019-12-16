package com.cliff.mytummy;

import java.util.ArrayList;
import java.util.List;

public class ambilData {
    private String nama;

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    private String deskripsi;
    private List<String> obat;
    private List<String> dilakukan;
    private List<String> dihindari;
    private List<String> gejala;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<String> getObat() {
        return obat;
    }

    public void setObat(List<String> obat) {
        this.obat = obat;
    }

    public List<String> getDilakukan() {
        return dilakukan;
    }

    public void setDilakukan(List<String> dilakukan) {
        this.dilakukan = dilakukan;
    }

    public List<String> getDihindari() {
        return dihindari;
    }

    public void setDihindari(List<String> dihindari) {
        this.dihindari = dihindari;
    }

    public ambilData(){}

    public ambilData(String nama,String deskripsi, ArrayList<String> obat, ArrayList<String> dilakukan, ArrayList<String> dihindari){
        this.nama = nama;
        this.obat = obat;
        this.dilakukan= dilakukan;
        this.dihindari = dihindari;
        this.deskripsi = deskripsi;
    }

    public List<String> getGejala() {
        return gejala;
    }

    public void setGejala(List<String> gejala) {
        this.gejala = gejala;
    }
}
