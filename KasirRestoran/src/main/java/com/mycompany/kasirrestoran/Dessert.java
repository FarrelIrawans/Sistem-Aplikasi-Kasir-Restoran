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
public class Dessert extends Menu {
    private static final long serialVersionUID = 1L;
    private String alergen;
    
    public Dessert(String kodeMenu, String namaMenu, double harga, String deskripsi, String alergen) {
        super(kodeMenu, namaMenu, harga, deskripsi);
        this.alergen = "N/A";
    }
    
    @Override
    public String getTipe(){
        return "Dessert";
    }
    
    @Override
    public void tampilInfo() { 
        super.tampilInfo();
        System.out.println("alergen: N/A");
    }
    
}
