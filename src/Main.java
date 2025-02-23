// PROGRAM K01-13523021-Tucil1-F01

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : Main.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Senin, 24 Februari 2025
// Deskripsi : Subprogram F01 - Main

// KAMUS
// Main : class
// main , deafult_solver , custom_solver , pyramid_solver : function
// s : String
// n , m , p : integer
// solved : boolean
// start , end , cnt : long
// board : matrix of character
// custom_board : list of String
// all_piece : list of PuzzlePiece
// all_set : matrix of PuzzlePiece
// pyramid : list of matrix of character

// ALGORITMA
package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int n , m , p;
    public static String s;
    public static char[][] board;
    public static char[][][] pyramid;
    public static boolean solved = false;
    public static List<String> custom_board = new ArrayList<>();
    public static List<Puzzle.PuzzlePiece> all_piece = new ArrayList<>();
    public static List<List<Puzzle.PuzzlePiece>> all_set = new ArrayList<>();
    public static long start , end , cnt = 0;

    public static void main(String[] args) {
        System.out.println("           Selamat Datang di Game IQ Puzzle Pro            ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                           MENU                            ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Main Game                                               ");
        System.out.println("2. Keluar                                                  ");
        System.out.println("-----------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
        System.out.print("Masukkan Pilihan Anda (1/2) : ");
        String string = scanner.nextLine().trim();
        while (!string.equals("EXIT") && !string.equals("1") && !string.equals("2")) {
            System.out.println("Error : Input Tidak Boleh Kosong / Tidak Valid!");
            System.out.print("Masukkan Pilihan Anda (1/2) : ");
            string = scanner.nextLine().trim();
        }
        if (string.equals("1")) {
            while (true) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("                        GAME START                         ");
                System.out.println("-----------------------------------------------------------");
                System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
                System.out.print("Masukkan nama file input (pakai .txt) : ");
                String file_name = scanner.nextLine().trim();
                while (file_name.isEmpty()) {
                    System.out.println("Error : Input Nama File Tidak Boleh Kosong!");
                    System.out.print("Masukkan nama file input (pakai .txt) : ");
                    file_name = scanner.nextLine().trim();
                }
                if (file_name.equals("EXIT")) {
                    break;
                } else {
                    boolean valid = ReadFile.read_file(file_name);
                    boolean force_stop = false;
                    while (!valid) {
                        System.out.print("Masukkan nama file input (pakai .txt) : ");
                        file_name = scanner.nextLine().trim();
                        while (file_name.isEmpty()) {
                            System.out.println("Error : Input Nama File Tidak Boleh Kosong!");
                            System.out.print("Masukkan nama file input (pakai .txt) : ");
                            file_name = scanner.nextLine().trim();
                        }
                        if (file_name.equals("EXIT")) {
                            force_stop = true;
                            break;
                        }
                        valid = ReadFile.read_file(file_name);
                    }
                    if (valid) {
                        String arg = "Tidak ada solusi yang ditemukan!";
                        start = System.currentTimeMillis();
                        if (s.equals("DEFAULT")) {
                            default_solver();
                        } else if (s.equals("CUSTOM")) {
                            custom_solver();
                        } else {
                            pyramid_solver();
                            if (!solved) {
                                arg = "Maaf, PYRAMID Solver Tidak Tersedia!\nTidak ada solusi yang ditemukan!";
                            }
                        }
                        end = System.currentTimeMillis();
                        long time = end - start;
                        System.out.printf("Waktu pencarian : %d ms.\n" , time);
                        System.out.printf("Banyak kasus yang ditinjau : %d kasus.\n" , cnt);
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("                SIMPAN HASIL KE FILE TEKS?                 ");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
                        System.out.print("Apakah Hasil Mau Anda Save Ke File Teks (y/n) : ");
                        String respond = scanner.nextLine().trim();
                        while (!respond.equals("EXIT") && !respond.equals("y") && !respond.equals("n")) {
                            System.out.println("Error : Input Tidak Boleh Kosong / Tidak Valid!");
                            System.out.print("Apakah Hasil Mau Anda Save Ke File Teks (y/n) : ");
                            respond = scanner.nextLine().trim();
                        }
                        if (respond.equals("EXIT")) {
                            break;
                        } else if (respond.equals("y")) {
                            System.out.println("-----------------------------------------------------------");
                            System.out.println("                  SISTEM PENYIMPANAN TEKS                  ");
                            System.out.println("-----------------------------------------------------------");
                            System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
                            System.out.print("Masukkan nama file teks output (pakai .txt) : ");
                            file_name = scanner.nextLine().trim();
                            while (file_name.isEmpty()) {
                                System.out.println("Error : Input Nama File Tidak Boleh Kosong!");
                                System.out.print("Masukkan nama file teks output (pakai .txt) : ");
                                file_name = scanner.nextLine().trim();
                            }
                            if (file_name.equals("EXIT")) {
                                break;
                            } else {
                                valid = SaveFile.save_text_2D(file_name , board , time , cnt , arg); 
                                while (!valid) {
                                    System.out.print("Masukkan nama file output (pakai .txt) : ");
                                    file_name = scanner.nextLine().trim();
                                    while (file_name.isEmpty()) {
                                        System.out.println("Error : Input Nama File Tidak Boleh Kosong!");
                                        System.out.print("Masukkan nama file output (pakai .txt) : ");
                                        file_name = scanner.nextLine().trim();
                                    }
                                    if (file_name.equals("EXIT")) {
                                        force_stop = true;
                                        break;
                                    }
                                    valid = SaveFile.save_text_2D(file_name , board , time , cnt , arg); 
                                }
                                if (force_stop) {
                                    break;
                                }
                            }
                        }
                        if (solved) {
                            System.out.println("-----------------------------------------------------------");
                            System.out.println("               SIMPAN HASIL KE FILE GAMBAR?                ");
                            System.out.println("-----------------------------------------------------------");
                            System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
                            System.out.print("Apakah Hasil Mau Anda Save Ke File Gambar (y/n) : ");
                            respond = scanner.nextLine().trim();
                            while (!respond.equals("EXIT") && !respond.equals("y") && !respond.equals("n")) {
                                System.out.println("Error : Input Tidak Boleh Kosong / Tidak Valid!");
                                System.out.print("Apakah Hasil Mau Anda Save Ke File Gambar (y/n) : ");
                                respond = scanner.nextLine().trim();
                            }
                            if (respond.equals("EXIT")) {
                                break;
                            } else if (respond.equals("y")) {
                                System.out.println("-----------------------------------------------------------");
                                System.out.println("                 SISTEM PENYIMPANAN GAMBAR                 ");
                                System.out.println("-----------------------------------------------------------");
                                System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
                                System.out.print("Masukkan nama file gambar output (pakai .png) : ");
                                file_name = scanner.nextLine().trim();
                                while (file_name.isEmpty()) {
                                    System.out.println("Error : Input Nama File Tidak Boleh Kosong!");
                                    System.out.print("Masukkan nama file gambar output (pakai .png) : ");
                                    file_name = scanner.nextLine().trim();
                                }
                                if (file_name.equals("EXIT")) {
                                    break;
                                } else {
                                    valid = SaveFile.save_image_2D(file_name , board);
                                    while (!valid) {
                                        System.out.print("Masukkan nama file gambar output (pakai .png) : ");
                                        file_name = scanner.nextLine().trim();
                                        while (file_name.isEmpty()) {
                                            System.out.println("Error : Input Nama File Tidak Boleh Kosong!");
                                            System.out.print("Masukkan nama file gambar output (pakai .png) : ");
                                            file_name = scanner.nextLine().trim();
                                        }
                                        if (file_name.equals("EXIT")) {
                                            force_stop = true;
                                            break;
                                        }
                                        valid = SaveFile.save_image_2D(file_name , board);
                                    }
                                    if (force_stop) {
                                        break;
                                    }
                                }
                            }
                        }
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("                           MENU                            ");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("1. Main Game                                               ");
                        System.out.println("2. Keluar                                                  ");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("WARNING : Input 'EXIT' To Force Exit!!");
                        System.out.print("Masukkan Pilihan Anda (1/2) : ");
                        string = scanner.nextLine().trim();
                        while (!string.equals("EXIT") && !string.equals("1") && !string.equals("2")) {
                            System.out.println("Error : Input Tidak Boleh Kosong / Tidak Valid!");
                            System.out.print("Masukkan Pilihan Anda (1/2) : ");
                            string = scanner.nextLine().trim();
                        }
                        if (string.equals("EXIT") || string.equals("2")) {
                            break;
                        }
                    } else if (force_stop) {
                        break;
                    }
                }
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------"); 
        System.out.println("            ALHAMDULILLAH TUCIL 1 STIMA SELESAI            ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                  BISMILLAH DAPAT A - 100                  ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("IDENTITAS PEMBUAT :                                        ");
        System.out.println("Nama : Muhammad Raihan Nazhim Oktana                       ");
        System.out.println("NIM  : 13523021 / IF-G / K-01                              ");
        System.out.println("-----------------------------------------------------------"); 
        scanner.close();
    }

    public static void default_solver() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("              PROSES PENCARIAN SOLUSI DEFAULT              ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        LOADING...                         ");
        System.out.println("-----------------------------------------------------------");
        board = new char[n][m];
        for (int x = 0 ; x < n ; x++) {
            for (int y = 0 ; y < m ; y++) {
                board[x][y] = '.'; 
            }
        }
        all_set.clear();
        for (Puzzle.PuzzlePiece e : all_piece) {
            all_set.add(e.generate());
        }
        Solve2D.solve_2D(0); 
        System.out.println("              HASIL PENCARIAN SOLUSI DEFAULT               ");
        System.out.println("-----------------------------------------------------------");
        if (solved) {
            Solve2D.show_board();
        } else {
            System.out.println("Tidak ada solusi yang ditemukan!");
        }
    }

    public static void custom_solver() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("               PROSES PENCARIAN SOLUSI CUSTOM              ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        LOADING...                         ");
        System.out.println("-----------------------------------------------------------");
        board = new char[n][m];
        for (int x = 0 ; x < n ; x++) {
            String line_board = custom_board.get(x);
            for (int y = 0 ; y < m ; y++) {
                char ch = line_board.charAt(y);
                if (ch == 'X') {
                    board[x][y] = '.';
                } else {
                    board[x][y] = ' ';
                }
            }
        }
        all_set.clear();
        for (Puzzle.PuzzlePiece e : all_piece) {
            all_set.add(e.generate());
        }
        Solve2D.solve_2D(0);
        System.out.println("               HASIL PENCARIAN SOLUSI CUSTOM               ");
        System.out.println("-----------------------------------------------------------");
        if (solved) {
            Solve2D.show_board();
        } else {
            System.out.println("Tidak ada solusi yang ditemukan!");
        }
    }

    public static void pyramid_solver() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("              PROSES PENCARIAN SOLUSI PYRAMID              ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        LOADING...                         ");
        System.out.println("-----------------------------------------------------------");
        pyramid = new char[n][m][m];
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < i + 1 ; j++) {
                for (int k = 0 ; k < i + 1 ; k++) {
                    pyramid[i][j][k] = '.';
                }
            }
        }
        // Solve3D.solve_3D(0);
        System.out.println("              HASIL PENCARIAN SOLUSI PYRAMID               ");
        System.out.println("-----------------------------------------------------------");
        if (solved) {
            Solve3D.show_pyramid();
        } else {
            System.out.println("Maaf, PYRAMID Solver Tidak Tersedia!");
            System.out.println("Tidak ada solusi yang ditemukan!");
        }
    }
}