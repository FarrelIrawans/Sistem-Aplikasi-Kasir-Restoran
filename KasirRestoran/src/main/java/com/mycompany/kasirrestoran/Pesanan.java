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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Pesanan implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idPesanan;
    private String statusPesanan;
    // Contoh: "Diterima", "Dimasak", "Selesai"
    
    // Wajib: Relasi Composition - Pesanan memiliki daftar objek Menu
    private List<Menu> daftarMenu; 

    // Constructor
    public Pesanan(String idPesanan) {
        this.idPesanan = idPesanan;
        this.statusPesanan = "Diterima"; // Status awal
        this.daftarMenu = new ArrayList<>();
    }

    // Method untuk menambah Pesanan
    public void tambahMenu(Menu menu) {
        this.daftarMenu.add(menu);
        System.out.println("  [+] " + menu.getNamaMenu() + " Pesanan Dibuat.");
    }

    // Method tambahan 1: Hitung total
    public double hitungTotal() {
        double total = 0;
        for (Menu item : daftarMenu) {
            total += item.getHarga();
        }
        return total;
    }
    
    // Method tambahan 2: Tampilkan detail pesanan
    public void tampilkanDetail() {
        System.out.println("========= Detail Pesanan ID: " + idPesanan + " ========");
        System.out.println("Status  : " + statusPesanan);
        System.out.println("Item    : ");
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu m = daftarMenu.get(i);
            System.out.println("  " + (i + 1) + ". [" + m.getTipe() + "] " + m.getNamaMenu() + " (Rp" + m.getHarga() + ")");
        }
        System.out.println("Total Harga: Rp" + String.format("%,.2f", hitungTotal()));
    }
    
    public static void tambahPesanan(){
        List<Menu> menuTersedia = Main.daftarMenu;
        List<Pesanan> semuaPesanan = Main.listPesanan;
        Scanner scanner = new Scanner(System.in);
        
        if (menuTersedia.isEmpty()) {
            System.out.println("Menu masih kosong! Tidak bisa membuat pesanan..");
            return;
        }
        
        String idPesananBaru = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Pesanan pesananBaru = new Pesanan(idPesananBaru);
        
        System.out.println("\n----- Membuat Pesanan Baru (ID: " + idPesananBaru + ") ------");
        String kode;
        boolean selesai = false;
        
        tampilkanDaftarMenuRingkas(menuTersedia);
        
        while (!selesai) {
            System.out.print("\nMasukkan kode menu (atau ketik 'SELESAI' untuk menyelesaikan pesanan): ");
            kode = scanner.nextLine().trim().toUpperCase();
            
            if (kode.equals("SELESAI")) {
                selesai = true;
                continue;
            }
            
            Menu menuDipilih = null;
            for (Menu menu : menuTersedia) {
                if (menu.getKodeMenu().equals(kode)) {
                    menuDipilih = menu;
                    break;
                }
            }
            
            if (menuDipilih != null) {
                // Relasi Composition: Menambah objek Menu ke dalam Pesanan
                pesananBaru.tambahMenu(menuDipilih); 
            } else {
                System.out.println("Kode menu *" + kode + "* tidak ditemukan. Silakan coba lagi.");
            }
        }
        
        if (pesananBaru.getDaftarMenu().isEmpty()) {
            System.out.println("Pesanan dibatalkan karena tidak ada item yang ditambahkan.");
        } else {
            semuaPesanan.add(pesananBaru);
            System.out.println("\n------ Pesanan ID " + idPesananBaru + " berhasil dibuat! ------");
            pesananBaru.tampilkanDetail();
        }
    }
    
    private static void tampilkanDaftarMenuRingkas(List<Menu> menuTersedia) {
        System.out.println("\n================== PILIH MENU ==================");
        System.out.printf("%-6s | %-15s | %-12s | %s\n", "KODE", "NAMA MENU", "HARGA", "TIPE");
        System.out.println("-------------------------------------------------");
        for (Menu menu : menuTersedia) {
            System.out.printf("%-6s | %-15s | Rp%,-10.2f | %s\n", 
                menu.getKodeMenu(), 
                menu.getNamaMenu(), 
                menu.getHarga(), 
                menu.getTipe());
        }
    }
    
    public static void kelolaPesanan() {
        Scanner scanner = Main.scanner;
        List<Pesanan> listPesanan = Main.listPesanan;
        
        if (listPesanan.isEmpty()) {
            System.out.println("\n----- Belum ada pesanan yang dibuat. -----");
            return;
        }

        tampilkanSemuaPesananRingkas(listPesanan);

        System.out.print("\nMasukkan ID Pesanan yang ingin dikelola: ");
        String idCari = scanner.nextLine().trim().toUpperCase();
        
        Pesanan pesananDitemukan = null;
        for (Pesanan p : listPesanan) {
            if (p.getIdPesanan().equals(idCari)) {
                pesananDitemukan = p;
                break;
            }
        }

        if (pesananDitemukan == null) {
            System.out.println("Pesanan dengan ID " + idCari + " tidak ditemukan.");
            return;
        }

        pesananDitemukan.tampilkanDetail();
        
        System.out.println("\nOpsi:");
        System.out.println("1. Ubah Status Pesanan");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.print("Pilih opsi: ");
        
        try {
            int opsi = Integer.parseInt(scanner.nextLine());
            if (opsi == 1) {
                ubahStatusPesanan(pesananDitemukan, scanner);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
        }
    }

    private static void ubahStatusPesanan(Pesanan pesanan, Scanner scanner) {
        System.out.println("\n------ Ubah Status Pesanan ID: " + pesanan.getIdPesanan() + " ------");
        System.out.println("Status saat ini: " + pesanan.getStatusPesanan());
        System.out.println("Pilihan Status Baru: ");
        System.out.println("1. Diterima");
        System.out.println("2. Dimasak");
        System.out.println("3. Selesai");
        System.out.print("Masukkan nomor status baru: ");
        
        String statusBaru = "";
        try {
            int pilihanStatus = Integer.parseInt(scanner.nextLine());
            switch (pilihanStatus) {
                case 1: statusBaru = "Diterima"; break;
                case 2: statusBaru = "Dimasak"; break;
                case 3: statusBaru = "Selesai"; break;
                default:
                    System.out.println("Pilihan status tidak valid.");
                    return;
            }
            pesanan.setStatusPesanan(statusBaru);
            System.out.println("\n------ Status Pesanan ID " + pesanan.getIdPesanan() + " berhasil diubah menjadi: " + statusBaru + " ------");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Pembatalan perubahan status.");
        }
    }
    
    private static void tampilkanSemuaPesananRingkas(List<Pesanan> listPesanan) {
        System.out.println("\n==============================================");
        System.out.println("             DAFTAR PESANAN AKTIF             ");
        System.out.println("==============================================");
        System.out.printf("%-10s | %-12s | %s\n", "ID PESANAN", "STATUS", "TOTAL HARGA");
        System.out.println("----------------------------------------------");
        for (Pesanan p : listPesanan) {
            System.out.printf("%-10s | %-12s | Rp%,.2f\n", 
                p.getIdPesanan(), 
                p.getStatusPesanan(), 
                p.hitungTotal());
        }
        System.out.println("==============================================");
    }
    // Getter/Setter untuk Ubah dan Cek Status Pesanan
    public String getIdPesanan() { return idPesanan; }
    public String getStatusPesanan() { return statusPesanan; }
    public void setStatusPesanan(String statusPesanan) { this.statusPesanan = statusPesanan; }
    public List<Menu> getDaftarMenu() { return daftarMenu; } // Diperlukan untuk fitur Ubah Pesanan (modifikasi item)
}
