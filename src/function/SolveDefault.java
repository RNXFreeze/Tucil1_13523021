// PROGRAM K01-13523021-Tucil1-F03B

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : SolveDefault.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F03B - Solve Default

// KAMUS
// ...

// ALGORITMA
package function;
import utils.Puzzle;

public class SolveDefault {
    public static SolveResult solveDefault(Puzzle puzzle) {
        long start = System.currentTimeMillis();
        int count = 0;
        String result = "Tidak ada solusi yang ditemukan!";
        long end = System.currentTimeMillis();
        long time = end - start;
        return new SolveResult(result , time , count);
    }
}