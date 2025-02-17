// PROGRAM K01-13523021-Tucil1-F03D

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : SolvePyramid.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Kamis, 20 Februari 2025
// Deskripsi : Subprogram F03D - Solve Pyramid

// KAMUS
// ...

// ALGORITMA
package function;
import utils.Puzzle;

public class SolvePyramid {
    public static SolveResult solvePyramid(Puzzle puzzle) {
        long start = System.currentTimeMillis();
        int count = 0;
        String result = "Mohon maaf, untuk saat ini, solusi pada tipe pyramid belum tersedia!";
        long end = System.currentTimeMillis();
        long time = end - start;
        return new SolveResult(result , time , count);
    }
}