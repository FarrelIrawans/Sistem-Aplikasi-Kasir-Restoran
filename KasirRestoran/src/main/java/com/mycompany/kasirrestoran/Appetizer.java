/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasirrestoran;

/**
 *
 * @author user
 */
// inheritance
public class Appetizer extends Menu {
    private static final long serialVersionUID = 1L;
    private String suhuSaji;
    private int porsiSaji;
    
    public Appetizer(String kodeMenu, String namaMenu, double harga, String deskripsi, String suhuSaji){
        super(kodeMenu, namaMenu, harga, deskripsi);
        this.suhuSaji = suhuSaji;
        this.porsiSaji = 1;
    }
    
    // method overriding: identifikasi tipe
    @Override
    public String getTipe(){
        return "Appetizer";
    }
    
    
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("Suhu Penyajian: " + suhuSaji);
    }
}
