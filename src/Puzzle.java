// PROGRAM K01-13523021-Tucil1-F02

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : Puzzle.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Senin, 24 Februari 2025
// Deskripsi : Subprogram F02 - Puzzle

// KAMUS
// ...

// ALGORITMA
package src;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {

    public static class Koordinat {
        public int x , y;
        public Koordinat(int x , int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class PuzzlePiece {
        public char name;
        public List<Koordinat> position;
        public int height;
        public int width;

        public PuzzlePiece(char name , List<Koordinat> position , int height , int width) {
            this.name = name;
            this.position = position;
            this.height = height;
            this.width = width;
        }

        public PuzzlePiece rotate() {
            List<Koordinat> npos = new ArrayList<>();
            int mh = height - 1;
            for (Koordinat e : position) {
                int nx = e.y;
                int ny = mh - e.x;
                npos.add(new Koordinat(nx , ny));
            }
            return normalize(name , npos);
        }

        public PuzzlePiece flip() {
            List<Koordinat> npos = new ArrayList<>();
            int mc = width - 1;
            for (Koordinat e : position) {
                int nx = e.x;
                int ny = mc - e.y;
                npos.add(new Koordinat(nx , ny));
            }
            return normalize(name , npos);
        }

        private static PuzzlePiece normalize(char id , List<Koordinat> opos) {
            int mir = Integer.MAX_VALUE , mic = Integer.MAX_VALUE;
            for (Koordinat e : opos) {
                mir = Math.min(mir , e.x);
                mic = Math.min(mic , e.y);
            }
            List<Koordinat> npos = new ArrayList<>();
            for (Koordinat e : opos) {
                npos.add(new Koordinat(e.x - mir , e.y - mic));
            }
            int mar = Integer.MIN_VALUE , mac = Integer.MIN_VALUE;
            for (Koordinat e : npos) {
                mar = Math.max(mar , e.x);
                mac = Math.max(mac , e.y);
            }
            int nh = mar + 1;
            int nw = mac + 1;
            return new PuzzlePiece(id , npos , nh , nw);
        }

        public List<PuzzlePiece> generate() {
            List<PuzzlePiece> res = new ArrayList<>();
            PuzzlePiece cur = this;
            for (int i = 0 ; i < 4 ; i++) {
                if (i > 0) {
                    cur = cur.rotate();
                }
                if (!check_list(res , cur)) {
                    res.add(cur);
                }
                PuzzlePiece flp = cur.flip();
                if (!check_list(res , flp)) {
                    res.add(flp);
                }
            }
            return res;
        }

        private boolean check_list(List<PuzzlePiece> list , PuzzlePiece comp) {
            for (PuzzlePiece e : list) {
                if (check_identical(e , comp)) {
                    return true;
                }
            }
            return false;
        }

        private boolean check_identical(PuzzlePiece a , PuzzlePiece b) {
            if (a.position.size() != b.position.size()) {
                return false;
            } else if (a.height != b.height || a.width != b.width) {
                return false;
            } else {
                for (Koordinat ea : a.position) {
                    boolean check = false;
                    for (Koordinat eb : b.position) {
                        if (ea.x == eb.x && ea.y == eb.y) {
                            check = true;
                            break;
                        }
                    }
                    if (!check) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}