import java.util.Scanner;

public class Main {

    // Daftar mahasiswa dan dosen yang terdaftar
    static Student[] students = new Student[100];
    static Teacher[] teachers = new Teacher[100];
    static int studentCount = 0;
    static int teacherCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println(" ------- SISTEM INFORMASI AKADEMIK - SIMULASI OOP ----- ");

        while (running) {
            System.out.println();
            System.out.println("  -------- MENU UTAMA --------    ");
            System.out.println("  1. Tambah Mahasiswa             ");
            System.out.println("  2. Tambah Dosen                 ");
            System.out.println("  3. Tambah Mata Kuliah ke Dosen  ");
            System.out.println("  4. Hapus Mata Kuliah dari Dosen ");
            System.out.println("  5. Tambah Nilai Mahasiswa       ");
            System.out.println("  6. Tampilkan Nilai Mahasiswa    ");
            System.out.println("  7. Hitung Rata-Rata Nilai       ");
            System.out.println("  8. Tampilkan Semua Data         ");
            System.out.println("  9. Ubah Alamat                  ");
            System.out.println("  0. Keluar                       ");
            System.out.print("Pilih menu [0-9]: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    tambahMahasiswa(scanner);
                    break;
                case "2":
                    tambahDosen(scanner);
                    break;
                case "3":
                    tambahMataKuliahDosen(scanner);
                    break;
                case "4":
                    hapusMataKuliahDosen(scanner);
                    break;
                case "5":
                    tambahNilaiMahasiswa(scanner);
                    break;
                case "6":
                    tampilkanNilaiMahasiswa(scanner);
                    break;
                case "7":
                    hitungRataRata(scanner);
                    break;
                case "8":
                    tampilkanSemuaData();
                    break;
                case "9":
                    ubahAlamat(scanner);
                    break;
                case "0":
                    System.out.println();
                    System.out.println("Terima kasih! Program selesai.");
                    running = false;
                    break;
                default:
                    System.out.println("  [!] Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    // ─── MENU 1: Tambah Mahasiswa ────────────────────────────────────────────────
    static void tambahMahasiswa(Scanner scanner) {
        System.out.println();
        System.out.println("── Tambah Mahasiswa ──");
        System.out.print("Nama mahasiswa : ");
        String nama = scanner.nextLine().trim();
        System.out.print("Alamat         : ");
        String alamat = scanner.nextLine().trim();

        if (nama.isEmpty()) {
            System.out.println("  [!] Nama tidak boleh kosong.");
            return;
        }

        Student s = new Student(nama, alamat);
        students[studentCount++] = s;
        System.out.println("  [OK] Mahasiswa berhasil ditambahkan: " + s);
    }

    // ─── MENU 2: Tambah Dosen ─────────────────────────────────────────────────────
    static void tambahDosen(Scanner scanner) {
        System.out.println();
        System.out.println("── Tambah Dosen ──");
        System.out.print("Nama dosen : ");
        String nama = scanner.nextLine().trim();
        System.out.print("Alamat     : ");
        String alamat = scanner.nextLine().trim();

        if (nama.isEmpty()) {
            System.out.println("  [!] Nama tidak boleh kosong.");
            return;
        }

        Teacher t = new Teacher(nama, alamat);
        teachers[teacherCount++] = t;
        System.out.println("  [OK] Dosen berhasil ditambahkan: " + t);
    }

    // ─── MENU 3: Tambah Mata Kuliah ke Dosen ─────────────────────────────────────
    static void tambahMataKuliahDosen(Scanner scanner) {
        System.out.println();
        System.out.println("── Tambah Mata Kuliah ke Dosen ──");

        if (teacherCount == 0) {
            System.out.println("  [!] Belum ada dosen terdaftar.");
            return;
        }

        tampilkanListDosen();
        System.out.print("Pilih nomor dosen: ");
        int idx = bacaAngka(scanner) - 1;
        if (idx < 0 || idx >= teacherCount) {
            System.out.println("  [!] Nomor dosen tidak valid.");
            return;
        }

        System.out.print("Nama mata kuliah: ");
        String mk = scanner.nextLine().trim();

        boolean berhasil = teachers[idx].addCourse(mk);
        if (berhasil) {
            System.out.println("  [+] Mata kuliah '" + mk + "' berhasil ditambahkan ke " + teachers[idx].getName());
        } else {
            System.out.println("  [!] Gagal: mata kuliah sudah ada (addCourse mengembalikan false).");
        }
    }

    // ─── MENU 4: Hapus Mata Kuliah dari Dosen ───────────────────────────────────
    static void hapusMataKuliahDosen(Scanner scanner) {
        System.out.println();
        System.out.println("── Hapus Mata Kuliah dari Dosen ──");

        if (teacherCount == 0) {
            System.out.println("  [!] Belum ada dosen terdaftar.");
            return;
        }

        tampilkanListDosen();
        System.out.print("Pilih nomor dosen: ");
        int idx = bacaAngka(scanner) - 1;
        if (idx < 0 || idx >= teacherCount) {
            System.out.println("  [!] Nomor dosen tidak valid.");
            return;
        }

        Teacher t = teachers[idx];
        String[] mk = t.getCourses();
        if (mk.length == 0) {
            System.out.println("  [!] Dosen ini belum memiliki mata kuliah.");
            return;
        }

        System.out.println("  Mata kuliah yang diampu:");
        for (int i = 0; i < mk.length; i++) {
            System.out.println("    " + (i + 1) + ". " + mk[i]);
        }
        System.out.print("Nama mata kuliah yang dihapus: ");
        String hapus = scanner.nextLine().trim();

        boolean berhasil = t.removeCourse(hapus);
        if (berhasil) {
            System.out.println("  [-] Mata kuliah '" + hapus + "' berhasil dihapus dari " + t.getName());
        } else {
            System.out.println("  [!] Gagal: mata kuliah tidak ditemukan (removeCourse mengembalikan false).");
        }
    }

    // ─── MENU 5: Tambah Nilai Mahasiswa ──────────────────────────────────────────
    static void tambahNilaiMahasiswa(Scanner scanner) {
        System.out.println();
        System.out.println("── Tambah Nilai Mahasiswa ──");

        if (studentCount == 0) {
            System.out.println("  [!] Belum ada mahasiswa terdaftar.");
            return;
        }

        tampilkanListMahasiswa();
        System.out.print("Pilih nomor mahasiswa: ");
        int idx = bacaAngka(scanner) - 1;
        if (idx < 0 || idx >= studentCount) {
            System.out.println("  [!] Nomor mahasiswa tidak valid.");
            return;
        }

        System.out.print("Nama mata kuliah: ");
        String mk = scanner.nextLine().trim();
        System.out.print("Nilai (0-100)   : ");
        int nilai = bacaAngka(scanner);

        students[idx].addCourseGrade(mk, nilai);
    }

    // ─── MENU 6: Tampilkan Nilai Mahasiswa ───────────────────────────────────────
    static void tampilkanNilaiMahasiswa(Scanner scanner) {
        System.out.println();
        System.out.println("── Tampilkan Nilai Mahasiswa ──");

        if (studentCount == 0) {
            System.out.println("  [!] Belum ada mahasiswa terdaftar.");
            return;
        }

        tampilkanListMahasiswa();
        System.out.print("Pilih nomor mahasiswa: ");
        int idx = bacaAngka(scanner) - 1;
        if (idx < 0 || idx >= studentCount) {
            System.out.println("  [!] Nomor mahasiswa tidak valid.");
            return;
        }

        students[idx].printGrades();
    }

    // ─── MENU 7: Hitung Rata-Rata Nilai ─────────────────────────────────────────
    static void hitungRataRata(Scanner scanner) {
        System.out.println();
        System.out.println("── Rata-Rata Nilai Mahasiswa ──");

        if (studentCount == 0) {
            System.out.println("  [!] Belum ada mahasiswa terdaftar.");
            return;
        }

        tampilkanListMahasiswa();
        System.out.print("Pilih nomor mahasiswa: ");
        int idx = bacaAngka(scanner) - 1;
        if (idx < 0 || idx >= studentCount) {
            System.out.println("  [!] Nomor mahasiswa tidak valid.");
            return;
        }

        Student s = students[idx];
        double avg = s.getAverageGrade();
        System.out.printf("  Rata-rata nilai %s : %.2f%n", s.getName(), avg);

        // Konversi ke huruf
        String grade;
        if (avg >= 85) grade = "A";
        else if (avg >= 75) grade = "B";
        else if (avg >= 65) grade = "C";
        else if (avg >= 55) grade = "D";
        else grade = "E";

        System.out.println("  Grade   : " + grade);
    }

    // ─── MENU 8: Tampilkan Semua Data ────────────────────────────────────────────
    static void tampilkanSemuaData() {
        System.out.println();
        System.out.println(" ------- DATA MAHASISWA (" + studentCount + " orang) ------- ");
        if (studentCount == 0) {
            System.out.println("  Tidak ada mahasiswa.");
        } else {
            for (int i = 0; i < studentCount; i++) {
                Student s = students[i];
                System.out.println((i + 1) + ". " + s);
                s.printGrades();
                System.out.printf("   Rata-rata: %.2f%n", s.getAverageGrade());
                System.out.println();
            }
        }

        System.out.println(" ------- DATA DOSEN (" + teacherCount + " orang) ------ ");
        if (teacherCount == 0) {
            System.out.println("  Tidak ada dosen.");
        } else {
            for (int i = 0; i < teacherCount; i++) {
                Teacher t = teachers[i];
                System.out.println((i + 1) + ". " + t);
                String[] mk = t.getCourses();
                if (mk.length == 0) {
                    System.out.println("   Mata kuliah: (belum ada)");
                } else {
                    System.out.print("   Mata kuliah: ");
                    for (int j = 0; j < mk.length; j++) {
                        System.out.print(mk[j]);
                        if (j < mk.length - 1) System.out.print(", ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    // ─── MENU 9: Ubah Alamat ────────────────────────────────────────────────────
    static void ubahAlamat(Scanner scanner) {
        System.out.println();
        System.out.println("── Ubah Alamat ──");
        System.out.println("  1. Mahasiswa");
        System.out.println("  2. Dosen");
        System.out.print("Pilih: ");
        String pilih = scanner.nextLine().trim();

        if (pilih.equals("1")) {
            if (studentCount == 0) { System.out.println("  [!] Belum ada mahasiswa."); return; }
            tampilkanListMahasiswa();
            System.out.print("Pilih nomor mahasiswa: ");
            int idx = bacaAngka(scanner) - 1;
            if (idx < 0 || idx >= studentCount) { System.out.println("  [!] Tidak valid."); return; }
            System.out.print("Alamat baru: ");
            String alamat = scanner.nextLine().trim();
            students[idx].setAddress(alamat);
            System.out.println("  [OK] Alamat " + students[idx].getName() + " diperbarui: " + students[idx]);
        } else if (pilih.equals("2")) {
            if (teacherCount == 0) { System.out.println("  [!] Belum ada dosen."); return; }
            tampilkanListDosen();
            System.out.print("Pilih nomor dosen: ");
            int idx = bacaAngka(scanner) - 1;
            if (idx < 0 || idx >= teacherCount) { System.out.println("  [!] Tidak valid."); return; }
            System.out.print("Alamat baru: ");
            String alamat = scanner.nextLine().trim();
            teachers[idx].setAddress(alamat);
            System.out.println("  [OK] Alamat " + teachers[idx].getName() + " diperbarui: " + teachers[idx]);
        } else {
            System.out.println("  [!] Pilihan tidak valid.");
        }
    }

    // ─── Helper ──────────────────────────────────────────────────────────────────
    static void tampilkanListMahasiswa() {
        System.out.println("  Daftar Mahasiswa:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("    " + (i + 1) + ". " + students[i].getName() + " (" + students[i].getAddress() + ")");
        }
    }

    static void tampilkanListDosen() {
        System.out.println("  Daftar Dosen:");
        for (int i = 0; i < teacherCount; i++) {
            System.out.println("    " + (i + 1) + ". " + teachers[i].getName() + " (" + teachers[i].getAddress() + ")");
        }
    }

    static int bacaAngka(Scanner scanner) {
        try {
            String line = scanner.nextLine().trim();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
