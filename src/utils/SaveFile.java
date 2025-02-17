// PROGRAM K01-13523021-Tucil1-F02D

// IDENTITAS
// NIM/Nama  : 13523021 - Muhammad Raihan Nazhim Oktana
// Instansi  : Sekolah Teknik Elektro dan Informatika (STEI-Komputasi) Institut Teknologi Bandung (ITB)
// Jurusan   : Teknik Informatika - Kampus Ganesha (IF-G)
// Nama File : SaveFile.java
// Topik     : Tugas Kecil 1 Strategi Algoritma 2025 (IF2211-24)
// Tanggal   : Selasa, 18 Februari 2025
// Deskripsi : Subprogram F02D - Utils Save File

// KAMUS
// ...

// ALGORITMA
package utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile {
    private static final String BASE_DIRECTORY = "../test/output";

    public static void saveFile(String content , String file_name) {
        try {
            String folder_name = BASE_DIRECTORY;
            File result = new File(folder_name);
            if (!result.exists()) {
                result.mkdirs();
            }
            File output = new File(result , file_name);
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write(content);
            writer.close();
            System.out.println("Success : File berhasil disimpan di : " + output.getAbsolutePath());
            System.out.println("-----------------------------------------------------------");
        } catch (IOException e) {
            System.err.println("Error : Gagal Menyimpan File : " + e.getMessage());
            System.out.println("-----------------------------------------------------------");
        }
    }
}