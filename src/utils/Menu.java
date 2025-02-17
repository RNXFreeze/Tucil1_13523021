// PROGRAM K01-13523021-Tucil1-F02A

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : Menu.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F02A - Utils Menu

// KAMUS
// ...

// ALGORITMA
package utils;

public class Menu {
    public static void welcome() {
        System.out.println("           Selamat Datang di Game IQ Puzzle Pro            ");
        System.out.println("-----------------------------------------------------------");
    }

    public static void menu() {
        System.out.println("                           MENU                            ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Main Game                                               ");
        System.out.println("2. Keluar                                                  ");
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuStartDEFAULT() {
        System.out.println("              PROSES PENCARIAN SOLUSI DEFAULT              ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        LOADING...                         ");
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuResultDEFAULT(String result , int time , int count) {
        System.out.println("              HASIL PENCARIAN SOLUSI DEFAULT               ");
        System.out.println("-----------------------------------------------------------");
        System.out.println(result);
        System.out.printf("Waktu pencarian : %d ms\n" , time);
        System.out.printf("Banyak kasus yang ditinjau : %d ms\n" , count);
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuStartCUSTOM() {
        System.out.println("              PROSES PENCARIAN SOLUSI CUSTOM               ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        LOADING...                         ");
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuResultCUSTOM(String result , int time , int count) {
        System.out.println("              HASIL PENCARIAN SOLUSI CUSTOM                ");
        System.out.println("-----------------------------------------------------------");
        System.out.println(result);
        System.out.printf("Waktu pencarian : %d ms\n" , time);
        System.out.printf("Banyak kasus yang ditinjau : %d ms\n" , count);
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuStartPYRAMID() {
        System.out.println("              PROSES PENCARIAN SOLUSI PYRAMID              ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        LOADING...                         ");
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuResultPYRAMID(String result , int time , int count) {
        System.out.println("              HASIL PENCARIAN SOLUSI PYRAMID               ");
        System.out.println("-----------------------------------------------------------");
        System.out.println(result);
        System.out.printf("Waktu pencarian : %d ms\n" , time);
        System.out.printf("Banyak kasus yang ditinjau : %d ms\n" , count);
        System.out.println("-----------------------------------------------------------");
    }

    public static void save() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("         APAKAH HASIL MAU ANDA SAVE KE FILE? (y/n)         ");
        System.out.println("-----------------------------------------------------------");
    }

    public static void credit() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------"); 
        System.out.println("            ALHAMDULILLAH TUCIL 1 STIMA SELESAI            ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                  BISMILLAH DAPAT A - 100                  ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("IDENTITAS PEMBUAT :                                        ");
        System.out.println("Nama : Muhammad Raihan Nazhim Oktana                       ");
        System.out.println("NIM  : 13523021                                            ");
        System.out.println("-----------------------------------------------------------"); 
    }
}