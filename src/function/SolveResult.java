// PROGRAM K01-13523021-Tucil1-F03A

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : SolveResult.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F03A - Solve Result

// KAMUS
// ...

// ALGORITMA
package function;

public class SolveResult {
    private String result;
    private long time;
    private int count;

    public SolveResult(String result, long time, int count) {
        this.result = result;
        this.time = time;
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }
}