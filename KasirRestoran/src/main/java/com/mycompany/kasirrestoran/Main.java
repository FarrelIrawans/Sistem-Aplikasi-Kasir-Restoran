/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasirrestoran;

/**
 *
 * @author user
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Main {
    // Data Aplikasi (Penyimpanan Dalam Memori)
    public static List<Menu> daftarMenu = new ArrayList<>();
    public static List<Pesanan> listPesanan = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    
    private static final String File_Menu = "data_menu.ser";
    private static final String File_Pesanan = "data_pesanan.ser";
    
    // Inisialisasi Menu Awal (Dummy Data)
    private static void inisialisasiMenu() {
        if (!muatData()){
            System.out.println("Loading.. ");
            daftarMenu.add(new Appetizer("A001", "Escargot", 125000, "Siput dengan butter herbs", "Hangat"));
            daftarMenu.add(new Appetizer("A002", "Salad Caesar", 95000, "Salad Romaine dengan dressing klasik", "Dingin"));
        
            daftarMenu.add(new MainCourse("M001", "Wagyu A5 Steak", 950000, "Sirloin Wagyu premium", "Daging Sapi"));
            daftarMenu.add(new MainCourse("M002", "Seared Salmon", 350000, "Salmon panggang dengan lemon butter", "Ikan Salmon"));
        
            daftarMenu.add(new Dessert("D001", "Chocolate Lava", 110000, "Kue cokelat leleh", "N/A"));
            daftarMenu.add(new Dessert("D002", "Tiramisu Klasik", 100000, "Kopi, keju mascarpone, dan ladyfingers", "N/A"));
        }
    }
    
    private static boolean muatData() {
        boolean berhasil = true;
        
        // Memuat Menu
        try (
            FileInputStream fileInMenu = new FileInputStream(File_Menu);
            ObjectInputStream objInMenu = new ObjectInputStream(fileInMenu);
        ) {
            daftarMenu = (List<Menu>) objInMenu.readObject();
            System.out.println("[INFO] Data Menu berhasil dimuat dari file.");
        } catch (FileNotFoundException e) {
            berhasil = false; // File belum ada, akan pakai dummy data
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] Gagal memuat data Menu: " + e.getMessage());
            berhasil = false;
        }
        
        // Memuat Pesanan
        try (
            FileInputStream fileInPesanan = new FileInputStream(File_Pesanan);
            ObjectInputStream objInPesanan = new ObjectInputStream(fileInPesanan);
        ) {
            listPesanan = (List<Pesanan>) objInPesanan.readObject();
            System.out.println("[INFO] Data Pesanan berhasil dimuat dari file.");
        } catch (FileNotFoundException e) {
            // Jika file menu ada tapi file pesanan tidak ada, tetap dianggap gagal secara keseluruhan
            berhasil = false;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] Gagal memuat data Pesanan: " + e.getMessage());
            berhasil = false;
        }
        return berhasil;
    }
    
    private static void simpanData() {
        try (
            FileOutputStream fileOutMenu = new FileOutputStream(File_Menu);
            ObjectOutputStream objOutMenu = new ObjectOutputStream(fileOutMenu);
            FileOutputStream fileOutPesanan = new FileOutputStream(File_Pesanan);
            ObjectOutputStream objOutPesanan = new ObjectOutputStream(fileOutPesanan);
        ) {
            objOutMenu.writeObject(daftarMenu);
            objOutPesanan.writeObject(listPesanan);
            System.out.println("\n[INFO] Data Menu dan Pesanan berhasil disimpan ke file.");
        } catch (IOException i) {
            System.out.println("\n[ERROR] Gagal menyimpan data: " + i.getMessage());
        }
    }
    
    public static void main(String[] args) {
        inisialisasiMenu();
        
        int pilihan = 0;
        do {
            System.out.println("\n================================================");
            System.out.println("         Aplikasi Kasir Restoran  V1.0          ");
            System.out.println("================================================");
            System.out.println("1. Daftar Menu ");
            System.out.println("2. Cari Menu (berdasarkan kategori) ");
            System.out.println("3. Tambahkan Pesanan ");
            System.out.println("4. Cek/Ubah Status Pesanan");
            System.out.println("5. Keluar");
            System.out.println("================================================");
            System.out.print("Pilih opsi: ");
        
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1:
                        Menu.tampilMenu();
                        break;
                    case 2:
                        Menu.cariMenu();
                        break;
                    case 3:
                        Pesanan.tambahPesanan();
                        break;
                    case 4:
                        Pesanan.kelolaPesanan();
                        break;
                    case 5:
                        simpanData();
                        System.out.println("Terima kasih. Sistem kasir ditutup.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                pilihan = 0; // Mengulang loop
            }
        } while (pilihan != 5);
    }
    // ... Implementasikan method lainnya di bawah ini ...
}
