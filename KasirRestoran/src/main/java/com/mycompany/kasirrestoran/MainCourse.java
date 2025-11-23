/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasirrestoran;

/**
 *
 * @author user
 */
// Inheritance
public class MainCourse extends Menu {
    private static final long serialVersionUID = 1L;
    private String bahanUtama;
    private int estimasiMasak;
    
    public MainCourse(String kodeMenu, String namaMenu, double harga, String deskripsi, String bahanUtama) {
        super(kodeMenu, namaMenu, harga, deskripsi);
        this.bahanUtama = bahanUtama;
        this.estimasiMasak = 20;
    }
    
    @Override
    public String getTipe() {
        return "Main Course";
    }
    
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("Bahan Utama: " + bahanUtama);
        System.out.println("Estimasi Waktu: " + estimasiMasak);
    }
}
