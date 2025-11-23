/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasirrestoran;

/**
 *
 * @author user
 */

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    // 4 Atribut private (Encapsulation)
    private String kodeMenu;
    private String namaMenu;
    private double harga;
    private String deskripsi;
    
    // constructor berparameter
    public Menu(String kodeMenu, String namaMenu, double harga, String deskripsi) {
        this.kodeMenu = kodeMenu;
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }
    
    // constructor kosong
    public Menu(){}
    
    // Getter: untuk menampilkan data dan perhitungan di class Pesanan
    public String getKodeMenu(){ return kodeMenu; }
    public String getNamaMenu(){ return namaMenu; }
    public double getHarga(){ return harga; }
    public String getDeskripsi(){ return deskripsi; }
    
    // Setter: untuk fitur "Ubah Menu" 
    public void setNamaMenu(String namaMenu){ this.namaMenu = namaMenu; }
    public void setHarga(double harga){ this.harga = harga; }
    public void setStatusMenu(String deskripsi){ this.deskripsi = deskripsi; }
    
    // method 1: info menu dasar
    public void tampilInfo(){
        System.out.println("Kode      : " + kodeMenu);
        System.out.println("Nama      : " + namaMenu);
        System.out.println("Harga     : Rp" + String.format("%,.2f", this.harga));
        System.out.println("Deskripsi : " + deskripsi);
    }
    
    // method 2: identifikasi tipe menu
    public String getTipe(){
        return "Menu Umum";
    }
    
    public void detailMenu() {
        tampilInfo(); // Memanggil method info dasar
        System.out.println("\nTipe Menu : " + getTipe()); // Menambahkan tipe menu
        System.out.println("================================================");
    }
    
    // method 3: Menampilkan semua menu
    public static void tampilMenu() {
        List<Menu> daftarMenu = Main.daftarMenu;
        
        if (daftarMenu.isEmpty()) {
            System.out.println("\n DAFTAR MENU KOSONG ");
            return;
        }

        for (Menu menu : daftarMenu) {
            menu.detailMenu();
        }
        System.out.println("================================================");
    }
    public static void cariMenu() {
        Scanner scanner = Main.scanner;
        List<Menu> daftarMenu = Main.daftarMenu;
        
        System.out.println("\n-------- Cari Menu Berdasarkan Kategori --------");
        System.out.println("Menu: Appetizer, Main Course, Dessert");
        System.out.print("Pilih Menu: ");
        String kategori = scanner.nextLine().trim();
        
        List<Menu> menuDitemukan = new ArrayList<>();
        // Menggunakan Polimorfisme: getTipe() yang di-override di subclass
        for (Menu menu : daftarMenu) {
            if (menu.getTipe().equalsIgnoreCase(kategori)) {
                menuDitemukan.add(menu);
            }
        }
        
        if (menuDitemukan.isEmpty()) {
            System.out.println("\n MENU DENGAN '" + kategori + "'. TIDAK ADA! ");
        } else {
            System.out.println("\n======== Hasil Pencarian Kategori: " + kategori + " ========");
            for (Menu menu : menuDitemukan) {
                menu.detailMenu();
            }
        }
    }
    
}
  