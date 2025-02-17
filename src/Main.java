// PROGRAM K01-13523021-Tucil1-F01

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : Main.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F01 - Main

// KAMUS
// ...

// ALGORITMA
import function.SolveCustom;
import function.SolveDefault;
import function.SolvePyramid;
import function.SolveResult;
import java.util.Scanner;
import utils.Menu;
import utils.ReadFile;
import utils.SaveFile;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Menu.welcome();
        while (!exit) {
            Menu.menu();
            System.out.print("Pilih opsi : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 :
                    SolveResult result = null;
                    System.out.print("Masukkan nama file input (pakai .txt) : ");
                    String file_name = scanner.nextLine();
                    System.out.println("-----------------------------------------------------------");
                    utils.Puzzle puzzle = ReadFile.readFile(file_name);
                    switch (puzzle.getS()) {
                        case "DEFAULT":
                            Menu.menuStartDEFAULT();
                            result = SolveDefault.solveDefault(puzzle);
                            Menu.menuResultDEFAULT(result.getResult() , (int) result.getTime() , result.getCount());
                            break;
                        case "CUSTOM":
                            Menu.menuStartCUSTOM();
                            result = SolveCustom.solveCustom(puzzle);
                            Menu.menuResultCUSTOM(result.getResult() , (int) result.getTime() , result.getCount());
                            break;
                        case "PYRAMID":
                            Menu.menuStartPYRAMID();
                            result = SolvePyramid.solvePyramid(puzzle);
                            Menu.menuResultPYRAMID(result.getResult() , (int) result.getTime() , result.getCount());
                            break;
                    }
                    if (result != null) {
                        Menu.save();
                        String res = result.getResult() + "\nWaktu pencarian : " + result.getTime() + " ms" + "\nBanyak kasus yang ditinjau : " + result.getCount() + "\n";
                        String save = scanner.nextLine();
                        if (save.equalsIgnoreCase("y")) {
                            System.out.print("Masukkan nama file output (pakai .txt) : ");
                            file_name = scanner.nextLine();
                            SaveFile.saveFile(res , file_name);
                        }
                    } else {
                        System.out.println("Mohon maaf, proses error!");
                    }
                    break;
                case 2 :
                    Menu.credit();
                    exit = true;
                    break;
                default :
                    System.out.println("Mohon maaf, input yang dimasukkan tidak valid!");
                    break;
            }
        }
        scanner.close();
    }
}