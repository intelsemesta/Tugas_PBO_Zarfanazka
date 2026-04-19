import java.util.Scanner;

public class MainNilai {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = sc.nextInt();
        sc.nextLine(); // consume newline

        NilaiMahasiswa[] mahasiswaArr = new NilaiMahasiswa[jumlah];

        // Input data mahasiswa
        for (int i = 0; i < jumlah; i++) {
            mahasiswaArr[i] = new NilaiMahasiswa();
            System.out.println("\n--- Mahasiswa ke-" + (i + 1) + " ---");
            System.out.print("NIM   : ");
            String nim = sc.nextLine();
            System.out.print("Nama  : ");
            String nama = sc.nextLine();
            System.out.print("Nilai : ");
            int nilai = sc.nextInt();
            sc.nextLine();

            mahasiswaArr[i].setDataMahasiswa(nim, nama, nilai);
            mahasiswaArr[i].hitungGrade();
        }

        System.out.println("\n=========================================");

        // Tampilkan data tiap mahasiswa
        for (NilaiMahasiswa mhs : mahasiswaArr) {
            mhs.tampilData();
        }

        // Hitung rekap
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        int jumlahA = 0, jumlahB = 0, jumlahC = 0, jumlahD = 0, jumlahE = 0;
        int totalNilai = 0;

        String namaLulus = "";
        String namaTidakLulus = "";
        String namaA = "", namaB = "", namaC = "", namaD = "", namaE = "";
        String nilaiStr = "";

        for (int i = 0; i < jumlah; i++) {
            NilaiMahasiswa mhs = mahasiswaArr[i];
            String grade = mhs.getGrade();
            String status = mhs.getStatus();
            totalNilai += mhs.getNilai();
            nilaiStr += (i == 0 ? "" : "+") + mhs.getNilai();

            if (status.equals("Lulus")) {
                jumlahLulus++;
                namaLulus += (namaLulus.isEmpty() ? "" : ", ") + mhs.getNama();
            } else {
                jumlahTidakLulus++;
                namaTidakLulus += (namaTidakLulus.isEmpty() ? "" : ", ") + mhs.getNama();
            }

            switch (grade) {
                case "A":
                    jumlahA++;
                    namaA += (namaA.isEmpty() ? "" : ", ") + mhs.getNama();
                    break;
                case "B":
                    jumlahB++;
                    namaB += (namaB.isEmpty() ? "" : ", ") + mhs.getNama();
                    break;
                case "C":
                    jumlahC++;
                    namaC += (namaC.isEmpty() ? "" : ", ") + mhs.getNama();
                    break;
                case "D":
                    jumlahD++;
                    namaD += (namaD.isEmpty() ? "" : ", ") + mhs.getNama();
                    break;
                case "E":
                    jumlahE++;
                    namaE += (namaE.isEmpty() ? "" : ", ") + mhs.getNama();
                    break;
            }
        }

        double rata = (double) totalNilai / jumlah;

        // Tampilkan rekap
        System.out.println("Jumlah Mahasiswa : " + jumlah);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + jumlahLulus +
                (namaLulus.isEmpty() ? "" : " yaitu " + namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus +
                (namaTidakLulus.isEmpty() ? "" : " yaitu " + namaTidakLulus));

        if (jumlahA > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai A = " + jumlahA + " yaitu " + namaA);
        if (jumlahB > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai B = " + jumlahB + " yaitu " + namaB);
        if (jumlahC > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai C = " + jumlahC + " yaitu " + namaC);
        if (jumlahD > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai D = " + jumlahD + " yaitu " + namaD);
        if (jumlahE > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai E = " + jumlahE + " yaitu " + namaE);

        System.out.println("Rata-rata nilai mahasiswa adalah : " + nilaiStr + " / " + jumlah + " = " + rata);

        sc.close();
    }
}