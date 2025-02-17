// PROGRAM K01-13523021-Tucil1-F02B

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : Puzzle.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F02B - Utils Puzzle

// KAMUS
// ...

// ALGORITMA
package utils;
import java.util.ArrayList;

public class Puzzle {
    private int N;
    private int M;
    private int P;
    private String S;
    private ArrayList<String> board;
    private ArrayList<String[]> shapes;

    public Puzzle(int N , int M , int P , String S , ArrayList<String> board , ArrayList<String[]> shapes) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.S = S;
        this.board = board;
        this.shapes = shapes;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public ArrayList<String> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<String> board1) {
        this.board = board1;
    }

    public ArrayList<String[]> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<String[]> shapes1) {
        this.shapes = shapes1;
    }
}