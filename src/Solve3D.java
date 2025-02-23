// PROGRAM K01-13523021-Tucil1-F04

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : Solve3D.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Senin, 24 Februari 2025
// Deskripsi : Subprogram F04 - Solve3D

// KAMUS
// Solve3D : class
// check_full_3D , solve_3D , check_spot_3D , fill_3D , show_pyramid : function
// RESET : String
// LIST_WARNA : List of String

// ALGORITMA
package src;
import src.Puzzle.Koordinat;
import src.Puzzle.PuzzlePiece;

public class Solve3D {

    public static final String RESET = "\u001B[0m";
    public static final String[] LIST_WARNA = {
        "\u001B[31m",       // Merah
        "\u001B[32m",       // Hijau
        "\u001B[33m",       // Kuning
        "\u001B[34m",       // Biru
        "\u001B[35m",       // Magenta
        "\u001B[36m",       // Cyan
        "\u001B[91m",       // Merah Terang
        "\u001B[92m",       // Hijau Terang
        "\u001B[93m",       // Kuning Terang
        "\u001B[94m",       // Biru Terang
        "\u001B[95m",       // Magenta Terang
        "\u001B[96m",       // Cyan Terang
        "\u001B[90m",       // Abu-abu Gelap
        "\u001B[37m",       // Abu-abu Terang
        "\u001B[38;5;160m", // Merah Gelap
        "\u001B[38;5;28m",  // Hijau Gelap
        "\u001B[38;5;220m", // Kuning Emas
        "\u001B[38;5;21m",  // Biru Gelap
        "\u001B[38;5;129m", // Lavender
        "\u001B[38;5;87m",  // Biru Langit Terang
        "\u001B[38;5;123m", // Biru Cyan Terang
        "\u001B[38;5;227m", // Kuning Cerah
        "\u001B[38;5;208m", // Oranye
        "\u001B[38;5;196m", // Merah Cerah
        "\u001B[38;5;245m", // Abu-abu Tua
        "\u001B[38;5;243m"  // Abu-abu Sedang
    };

    public static boolean check_full_3D() {
        for (int i = 0 ; i < Main.n ; i++) {
            for (int j = 0 ; j < Main.m ; j++) {
                for (int k = 0 ; k < Main.m ; k++) {
                    if (Main.pyramid[i][j][k] == '.') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void solve_3D(int idx) {
        if (!Main.solved) {
            if (idx == Main.p) {
                if (check_full_3D()) {
                    Main.solved = true;
                }
            } else {
                for (PuzzlePiece e : Main.all_set.get(idx)) {
                    for (int x = 0 ; x < Main.n ; x++) {
                        for (int y = 0 ; y < Main.m ; y++) {
                            for (int z = 0 ; z < Main.m ; z++) {
                                Main.cnt++;
                                if (check_spot_3D(e , x , y , z)) {
                                    fill_3D(e , x , y , z , e.name);
                                    solve_3D(idx + 1);
                                    if (!Main.solved) {
                                        fill_3D(e , x , y , z , '.');
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean check_spot_3D(PuzzlePiece piece , int px , int py , int pz) {
        for (Koordinat e : piece.position) {
            int rx = px + e.x;
            int ry = py + e.y;
            int rz = pz + e.x + e.y;
            if (rx < 0 || rx >= Main.n || ry < 0 || ry >= Main.m || rz < 0 || rz >= Main.m) {
                return false;
            } else if (Main.pyramid[rx][ry][rz] != '.') {
                return false;
            }
        }
        return true;
    }

    public static void fill_3D(PuzzlePiece piece , int px , int py , int pz , char id) {
        for (Koordinat e : piece.position) {
            int rx = px + e.x;
            int ry = py + e.y;
            int rz = pz + e.x + e.y;
            Main.pyramid[rx][ry][rz] = id;
        }
    }

    public static void show_pyramid() {
        for (int x = 0 ; x < Main.n ; x++) {
            for (int y = 0 ; y < x + 1 ; y++) {
                for (int z = 0 ; z < x + 1 ; z++) {
                    char c = Main.pyramid[x][y][z];
                    if (c >= 'A' && c <= 'Z') {
                        int idx = c - 'A'; 
                        if (idx >= 0 && idx < LIST_WARNA.length) {
                            System.out.print(LIST_WARNA[idx] + c + RESET);
                        } else {
                            System.out.print(c);
                        }
                    } else {
                        System.out.print(c);
                    }
                }
                System.out.println();
            }
        }
    }
}