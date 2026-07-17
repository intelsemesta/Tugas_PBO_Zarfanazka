import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "students.csv";
        String line;
        String csvSplitBy = ",";
        int indeks = 0;
        int jumlahBaris = 0; // penghitung jumlah baris data (tidak termasuk header)

        System.out.println("NIM, NAMA, UMUR, PRODI");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                indeks++;
                if (indeks > 1) { // lewati baris header
                    String[] student = line.split(csvSplitBy);
                    System.out.println(student[0] + ", " + student[1] + ", " + student[2] + ", " + student[3]);
                    jumlahBaris++; // tambah setiap kali ada baris data
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Menampilkan jumlah baris data dalam file
        System.out.println("\nJumlah baris data dalam students.csv: " + jumlahBaris);
    }
}
