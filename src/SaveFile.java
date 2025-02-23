// PROGRAM K01-13523021-Tucil1-F06

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : SaveFile.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Senin, 24 Februari 2025
// Deskripsi : Subprogram F06 - Save File

// KAMUS
// SaveFile : class
// save_text_2D , save_image_2D : Function
// BASE_DIRECTORY_OUT : String

// ALGORITMA
package src;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

public class SaveFile {

    public static final String BASE_DIRECTORY_OUT = "../test/output/";
    public static final Color[] colorMap = {
        new Color(255 , 0 , 0),      // Merah
        new Color(0 , 255 , 0),      // Hijau
        new Color(255 , 255 , 0),    // Kuning
        new Color(0 , 0 , 255),      // Biru
        new Color(255 , 0 , 255),    // Magenta
        new Color(0 , 255 , 255),    // Cyan
        new Color(255 , 175 , 175),  // Pink Muda
        new Color(64 , 64 , 64),     // Abu-abu Gelap
        new Color(255 , 165 , 0),    // Oranye
        new Color(192 , 192 , 192),  // Abu-abu Terang
        new Color(128 , 128 , 128),  // Abu-abu
        new Color(255 , 105 , 180),  // Pink Cerah
        new Color(100 , 150 , 200),  // Biru Muda Custom
        new Color(150 , 100 , 200),  // Ungu Muda Custom
        new Color(210 , 105 , 30),   // Coklat
        new Color(255 , 69 , 0),     // Merah Oranye
        new Color(154 , 205 , 50),   // Kuning Hijau
        new Color(255 , 215 , 0),    // Emas
        new Color(0 , 191 , 255),    // Biru Langit Cerah
        new Color(199 , 21 , 133),   // Merah Magenta
        new Color(72 , 209 , 204),   // Biru Hijau Medium
        new Color(218 , 112 , 214),  // Orchid
        new Color(188 , 143 , 143),  // Pink Abu-Abu
        new Color(255 , 140 , 0),    // Oranye Gelap
        new Color(173 , 216 , 230),  // Biru Muda
        new Color(244 , 164 , 96)    // Pasir
    };
    
    public static boolean save_text_2D(String file_name , char[][] board , long time , long cnt , String arg) {
        String[] list_illegal_char = {"/" , "\\" , ":" , "*" , "?" , "\"" , "<" , ">" , "|"};
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
            System.out.println("Error : Gagal Menyimpan File Teks : Nama file mengandung karakter yang tidak diizinkan (" + found_illegal + ").");
            return false;
        } else if (!file_name.endsWith(".txt")) {
            System.out.println("Error : Gagal Menyimpan File Teks : Format file harus (.txt).");
            return false;
        } else {
            String file_path = BASE_DIRECTORY_OUT + "text/" + file_name;
            File file = new File(file_path);
            try (PrintWriter writer = new PrintWriter(file)) {
                if (Main.solved) {
                    for (int i = 0 ; i < board.length ; i++) {
                        for (int j = 0 ; j < board[0].length ; j++) {
                            writer.print(board[i][j]);
                        }
                        writer.println();
                    }
                } else {
                    writer.println(arg);
                }
                writer.println("Waktu pencarian : " + time + " ms.");
                writer.println("Banyak kasus yang ditinjau : " + cnt + " kasus.");
                System.out.println("Success : File Teks Berhasil Disimpan Di : " + file_path);
                return true;
            } catch (Exception ex) {
                System.out.println("Error : Gagal Menyimpan File Teks : " + ex.getMessage());
                return false;
            }
        }
    }

    public static boolean save_image_2D(String file_name , char[][] board_data) {
        String[] list_illegal_char = {"/" , "\\" , ":" , "*" , "?" , "\"" , "<" , ">" , "|"};
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
            System.out.println("Error : Gagal Menyimpan File Gambar : Nama file mengandung karakter yang tidak diizinkan (" + found_illegal + ").");
            return false;
        } else if (!file_name.endsWith(".png")) {
            System.out.println("Error : Gagal Menyimpan File Gambar : Format file harus (.png).");
            return false;
        } else {
            try {
                String file_path = BASE_DIRECTORY_OUT + "image/" + file_name;
                File file = new File(file_path);
                int r = board_data.length;
                int c = board_data[0].length;
                int size = 30;
                int w = c * size;
                int h = r * size;
                BufferedImage image = new BufferedImage(w , h , BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0 , 0 , w , h);
                for (int x = 0 ; x < r ; x++) {
                    for (int y = 0 ; y < c ; y++) {
                        char ch = board_data[x][y];
                        Color color = Color.LIGHT_GRAY;
                        if (ch == '.') {
                            color = Color.WHITE;
                        }
                        else if ('A' <= ch && ch <= 'Z') {
                            int idx = ch - 'A';
                            if (idx >= 0 && idx < colorMap.length) {
                                color = colorMap[idx];
                            }
                        } else if (ch == ' ') {
                            color = Color.BLACK;
                        }
                        int xx = y*size;
                        int yy = x*size;
                        g.setColor(color);
                        g.fillRect(xx , yy , size , size);
                        g.setColor(Color.BLACK);
                        g.drawRect(xx , yy , size , size);
                    }
                }
                g.dispose();
                String format = "png";
                javax.imageio.ImageIO.write(image , format , file);
                System.out.println("Success : File Gambar Berhasil Disimpan Di : " + file_path);
                return true;
            } catch (Exception ex) {
                System.out.println("Error : Gagal Menyimpan File Gambar : " + ex.getMessage());
                return false;
            }
        }
    }
}