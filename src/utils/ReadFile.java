// PROGRAM K01-13523021-Tucil1-F02C

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : ReadFile.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F02C - Utils Read File

// KAMUS
// ...

// ALGORITMA
package utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    private static final String BASE_DIRECTORY = "../test/input/";

    public static Puzzle readFile(String file_name) {
        String file_path = BASE_DIRECTORY + file_name;
        Puzzle result = null;
        try {
            File input = new File(file_path);
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("Mohon maaf, informasi N M P tidak lengkap!");
            }
            String[] header = line.trim().split("\\s+");
            if (header.length != 3) {
                throw new IllegalArgumentException("Mohon maaf, informasi N M P tidak sesuai!");
            }
            int n = Integer.parseInt(header[0]);
            int m = Integer.parseInt(header[1]);
            int p = Integer.parseInt(header[2]);
            if (n < 1 || m < 1 || p < 1 || p > 26) {
                throw new NumberFormatException("Nilai N M P harus memenuhi : 1 <= n , 1 <= m , 1 <= p <= 26.");
            }
            line = reader.readLine();
            if (line == null) {
                throw new IOException("Mohon maaf, informasi tipe permainan (S) tidak lengkap!");
            }
            String s = line.trim();
            if (!s.equals("DEFAULT") && !s.equals("CUSTOM") && !s.equals("PYRAMID")) {
                throw new IllegalArgumentException("Mohon maaf, tipe permainan (S) harus DEFAULT / CUSTOM / PYRAMID.");
            }
            ArrayList<String> board = new ArrayList<>();
            ArrayList<String[]> shapes = new ArrayList<>();
            if ("DEFAULT".equalsIgnoreCase(s)) {
                for (int i = 0 ; i < n ; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0 ; j < m ; j++) {
                        sb.append("X");
                    }
                    board.add(sb.toString());
                }
            } else if ("CUSTOM".equalsIgnoreCase(s)) {
                for (int i = 0 ; i < n ; i++) {
                    line = reader.readLine();
                    if (line == null) {
                        throw new IOException("Mohon maaf, informasi papan permainan tidak lengkap!");
                    }
                    if (line.trim().length() != m) {
                        throw new IOException("Mohon maaf, informasi papan permainan tidak sesuai!");
                    }
                    if (!line.trim().matches("[.X]+")) {
                        throw new IllegalArgumentException("Papan permainan CUSTOM hanya boleh mengandung karakter '.' dan 'X'.");
                    }
                    board.add(line.trim());
                }
            } else if ("PYRAMID".equalsIgnoreCase(s)) {
                for (int i = 1 ; i <= n ; i++) {
                    for (int j = 0 ; j < i ; j++) {
                        StringBuilder sb = new StringBuilder();
                        for (int k = 0 ; k < i ; k++) {
                            sb.append("X");
                        }
                        board.add(sb.toString());
                    }
                }
            }
            for (int i = 0 ; i < p ; i++) {
                ArrayList<String> lines = new ArrayList<>();
                line = reader.readLine();
                while (line != null && !line.isEmpty()) {
                    lines.add(line.trim());
                    line = reader.readLine();
                }
                shapes.add(lines.toArray(new String[0]));
            }
            result = new Puzzle(n , m , p , s , board , shapes);
            reader.close();
        } catch (IOException e) {
            System.err.println("Error : Gagal Membaca File : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error : Format nomor salah pada input N M P.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error : " + e.getMessage());
        }
        return result;
    }
}
