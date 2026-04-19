public class NilaiMahasiswa extends Mahasiswa {
    protected String grade;
    protected String status;

    public void hitungGrade() {
        if (nilai > 100 || nilai < 0) {
            grade = "Input nilai anda salah";
            status = "-";
        } else if (nilai >= 80) {
            grade = "A";
            status = "Lulus";
        } else if (nilai >= 70) {
            grade = "B";
            status = "Lulus";
        } else if (nilai >= 60) {
            grade = "C";
            status = "Lulus";
        } else if (nilai >= 50) {
            grade = "D";
            status = "Tidak Lulus";
        } else {
            grade = "E";
            status = "Tidak Lulus";
        }
    }

    public String getGrade() {
        return grade;
    }

    public String getStatus() {
        return status;
    }

    public void tampilData() {
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + nama);
        System.out.println("Nilai : " + nilai);
        System.out.println("Grade : " + grade);
        System.out.println("=========================================");
    }
}
