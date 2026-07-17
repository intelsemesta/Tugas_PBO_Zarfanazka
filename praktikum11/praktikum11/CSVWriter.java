import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "new_students.csv";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah data mahasiswa yang ingin ditambahkan: ");
        int jumlahData = Integer.parseInt(scanner.nextLine());

        String[] data = new String[jumlahData];

        for (int i = 0; i < jumlahData; i++) {
            System.out.println("\nData mahasiswa ke-" + (i + 1) + ":");

            System.out.print("NIM   : ");
            String nim = scanner.nextLine();

            System.out.print("Nama  : ");
            String nama = scanner.nextLine();

            System.out.print("Umur  : ");
            String umur = scanner.nextLine();

            System.out.print("Prodi : ");
            String prodi = scanner.nextLine();

            // Menggabungkan input menjadi satu baris CSV
            data[i] = nim + "," + nama + "," + umur + "," + prodi;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("\nData berhasil disimpan ke " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
