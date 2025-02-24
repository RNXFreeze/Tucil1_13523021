// PROGRAM K01-13523021-Tucil1-F05

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : ReadFile.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Senin, 24 Februari 2025
// Deskripsi : Subprogram F05 - Read File

// KAMUS
// ReadFile : class
// read_file , build_piece , connected : Function
// BASE_DIRECTORY_IN : String

// ALGORITMA
package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import src.Puzzle.PuzzlePiece;

public class ReadFile {

    public static final String BASE_DIRECTORY_IN = "../test/input/";

    public static boolean read_file(String file_name) {
        String[] list_illegal_char = {"/" , "\\" , ":" , "*" , "?" , "\"" , "<" , ">" , "|"};
        String[] allowed_name = {"A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" , "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" , "U" , "V" , "W" , "X" , "Y" , "Z"};
        StringBuilder found_illegal = new StringBuilder();
        for (String e : list_illegal_char) {
            if (file_name.contains(e)) {
                if (found_illegal.length() > 0) {
                    found_illegal.append(" , ");
                }
                found_illegal.append(e);
            }
        }
        if (found_illegal.length() > 0) {
            System.out.println("Error : Gagal Membaca File : Nama file mengandung karakter yang tidak diizinkan (" + found_illegal + ").");
            return false;
        } else {
            Main.custom_board.clear(); 
            Main.all_piece.clear();
            Main.all_set.clear();
            Main.solved = false;
            Main.cnt = 0;
            if (!file_name.endsWith(".txt")) {
                System.out.println("Error : Gagal Membaca File : Format file harus (.txt).");
                return false;
            } else {
                String file_path = BASE_DIRECTORY_IN + file_name;
                File file = new File(file_path);
                if (!file.exists()) {
                    System.out.println("Error : Gagal Membaca File : " + file_path + " tidak ditemukan.");
                    return false;
                } else {
                    try {
                        Scanner scn = new Scanner(file);
                        if (!scn.hasNextInt()) {
                            System.out.println("Error : N bukan integer.");
                            scn.close();
                            return false;
                        } else {
                            Main.n = scn.nextInt();
                        }
                        if (!scn.hasNextInt()) {
                            System.out.println("Error : M bukan integer.");
                            scn.close();
                            return false;
                        } else {
                            Main.m = scn.nextInt();
                        }
                        if (!scn.hasNextInt()) {
                            System.out.println("Error : P bukan integer.");
                            scn.close();
                            return false;
                        } else {
                            Main.p = scn.nextInt();
                        }
                        if (Main.n < 1 || Main.m < 1 || Main.p < 1) {
                            System.out.println("Error : N , M , P harus berupa bilangan bulat positif.");
                            scn.close();
                            return false;
                        } else if (Main.p > 26) {
                            System.out.println("Error : P harus berupa bilagan bulat positif yang tidak lebih dari 26.");
                            scn.close();
                            return false;
                        }
                        if (!scn.hasNext()) {
                            System.out.println("Error : Tidak ada tipe puzzle (DEFAULT / CUSTOM / PYRAMID).");
                            scn.close();
                            return false;
                        } else {
                            Main.s = scn.next().trim();
                            scn.nextLine();
                            if (!Main.s.equals("DEFAULT") && !Main.s.equals("CUSTOM") && !Main.s.equals("PYRAMID")) {
                                System.out.println("Error : Tipe puzzle hanya ada DEFAULT / CUSTOM / PYRAMID.");
                                scn.close();
                                return false;
                            }
                        }
                        if (Main.s.equals("CUSTOM")) {
                            for (int x = 0 ; x < Main.n ; x++) {
                                if (!scn.hasNextLine()) {
                                    System.out.println("Error : Baris papan permainan kurang dari " + Main.n + " baris.");
                                    scn.close();
                                    return false;
                                } else {
                                    String board_line = scn.nextLine();
                                    if (board_line.length() != Main.m) {
                                        System.out.println("Error : Panjang baris ke-" + (x + 1) + " bukan " + Main.m + ".");
                                        scn.close();
                                        return false;
                                    } else {
                                        Main.custom_board.add(board_line); 
                                        for (int y = 0 ; y < Main.m ; y++) {
                                            char c = board_line.charAt(y);
                                            if (c != '.' && c != 'X') {
                                                System.out.println("Error : Board hanya boleh '.' atau 'X'.");
                                                scn.close();
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (Main.s.equals("PYRAMID")) {
                            if (Main.n != Main.m) {
                                System.out.println("Error : Pada tipe puzzle PYRAMID nilai N dan M harus sama.");
                                scn.close();
                                return false;
                            }
                        }
                        List<Character> vname = new ArrayList<>();
                        scn.useDelimiter("\r?\n");
                        String line_comp = null;
                        for (int i = 0 ; i < Main.p ; i++) {
                            char target = (char) ('A' + i);
                            List<String> piece_line = new ArrayList<>();
                            boolean check_piece = false;
                            if (line_comp != null) {
                                if (line_comp.trim().isEmpty()) {
                                    break;
                                }
                                char f = line_comp.trim().charAt(0);
                                if ((!Arrays.asList(allowed_name).contains(String.valueOf(f)))) {
                                    System.out.println("Error : Potongan puzzle memiliki karakter yang tidak valid.");
                                    scn.close();
                                    return false;
                                }
                                target = f;
                                for (int j = 0 ; j < line_comp.length() ; j++) {
                                    if (line_comp.charAt(j) != ' ' && line_comp.charAt(j) != target) {
                                        System.out.println("Error : Baris potongan puzzle mengandung huruf campuran : " + line_comp + ".");
                                        scn.close();
                                        return false;
                                    } else if ((!Arrays.asList(allowed_name).contains(String.valueOf(line_comp.charAt(j))))) {
                                        System.out.println("Error : Potongan puzzle memiliki karakter yang tidak valid.");
                                        scn.close();
                                        return false;
                                    }
                                }
                                if (vname.contains(target)) {
                                    System.out.println("Error : Terdapat duplikat potongan puzzle '" + target + "'.");
                                    scn.close();
                                    return false;
                                } else {
                                    vname.add(target);
                                }
                                piece_line.add(line_comp);
                                check_piece = true;
                                line_comp = null;
                            }
                            boolean over = false;
                            while (scn.hasNextLine()) {
                                String line = scn.nextLine();
                                if (line.trim().isEmpty()) {
                                    break;
                                }
                                char f = line.trim().charAt(0);
                                if ((!Arrays.asList(allowed_name).contains(String.valueOf(f)))) {
                                    System.out.println("Error : Potongan puzzle memiliki karakter yang tidak valid.");
                                    scn.close();
                                    return false;
                                }
                                if (i == 0 && !over) {
                                    target = f;
                                    over = true;
                                    vname.add(target);
                                }
                                if (f != target) {
                                    if (i == Main.p - 1) {
                                        System.out.println("Error : Terlalu banyak potongan. Seharusnya hanya " + Main.p + " potongan.");
                                        scn.close();
                                        return false;
                                    } else {
                                        line_comp = line;
                                        break;
                                    }
                                }
                                for (int c = 0 ; c < line.length() ; c++) {
                                    char ch = line.charAt(c);
                                    if (ch != ' ' && ch != target) {
                                        System.out.println("Error : Baris potongan puzzle mengandung huruf campuran : " + line + ".");
                                        scn.close();
                                        return false;
                                    } else if ((!Arrays.asList(allowed_name).contains(String.valueOf(ch)))) {
                                        System.out.println("Error : Potongan puzzle memiliki karakter yang tidak valid.");
                                        scn.close();
                                        return false;
                                    }
                                }
                                piece_line.add(line);
                                check_piece = true;
                            }
                            if (!check_piece) {
                                System.out.println("Error : Potongan puzzle ke-" + (i + 1) + " tidak ada di data.");
                                scn.close();
                                return false;
                            }
                            PuzzlePiece piece = build_piece(piece_line , target);
                            if (!connected(piece.position)) {
                                System.out.println("Error : Potongan puzzle ke-" + (i + 1) + " tidak terhubung.");
                                scn.close();
                                return false;
                            } else {
                                Main.all_piece.add(piece);
                            }
                        }
                        scn.close();
                        return true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Error : Gagal Membaca File : File " + file_name + " tidak ditemukan.");
                        return false;
                    } catch (Exception e) {
                        System.out.println("Error : Gagal Membaca File : " + e.getMessage());
                        return false;
                    }
                }
            }
        }
    }

    private static PuzzlePiece build_piece(List<String> str_shape , char name) {
        List<Puzzle.Koordinat> opos = new ArrayList<>();
        int mir = Integer.MAX_VALUE , mic = Integer.MAX_VALUE;
        int mar = Integer.MIN_VALUE , mac = Integer.MIN_VALUE;
        for (int x = 0 ; x < str_shape.size() ; x++) {
            String str = str_shape.get(x);
            for (int y = 0 ; y < str.length() ; y++) {
                char c = str.charAt(y);
                if (c == name) {
                    opos.add(new Puzzle.Koordinat(x , y));
                    mir = Math.min(mir , x);
                    mic = Math.min(mic , y);
                    mar = Math.max(mar , x);
                    mac = Math.max(mac , y);
                }
            }
        }
        List<Puzzle.Koordinat> npos = new ArrayList<>();
        for (Puzzle.Koordinat e : opos) {
            npos.add(new Puzzle.Koordinat(e.x - mir , e.y - mic));
        }
        int nh = mar - mir + 1;
        int nw = mac - mic + 1;
        return new PuzzlePiece(name , npos , nh , nw);
    }

    private static boolean connected(List<Puzzle.Koordinat> vpos) {
        List<Puzzle.Koordinat> vvis = new ArrayList<>();
        Stack<Puzzle.Koordinat> stack = new Stack<>();
        stack.push(vpos.get(0));
        while (!stack.isEmpty()) {
            Puzzle.Koordinat cur = stack.pop();
            if (!vvis.contains(cur)) {
                vvis.add(cur);
                int[] dx = {1 , 1 , 1 , 0 , 0 , -1 , -1 , -1};
                int[] dy = {1 , 0 , -1 , 1 , -1 , 1 , 0 , -1};
                for (int i = 0 ; i < 8 ; i++) {
                    Puzzle.Koordinat npos = new Puzzle.Koordinat(cur.x + dx[i] , cur.y + dy[i]);
                    if (vpos.contains(npos) && !vvis.contains(npos)) {
                        stack.push(npos);
                    }
                }
            }
        }
        return vvis.size() == vpos.size();
    }
}