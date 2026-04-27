package com.kampus.main;

import com.kampus.collection.Repository;
import com.kampus.model.Dosen;
import com.kampus.model.Mahasiswa;
import com.kampus.abstrak.Orang;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   APLIKASI MANAJEMEN KAMPUS          ║");
        System.out.println("║   Generic Collection - Java          ║");
        System.out.println("╚══════════════════════════════════════╝");

        // ─── Repository Mahasiswa ────────────────────────
        Repository<Mahasiswa> repoMahasiswa = new Repository<>("Data Mahasiswa");

        Mahasiswa m1 = new Mahasiswa("1102020", "Ferdi",  21, "Informatika",    3.75);
        Mahasiswa m2 = new Mahasiswa("1102021", "Sari",   20, "Sistem Informasi", 3.90);
        Mahasiswa m3 = new Mahasiswa("1102022", "Budi",   22, "Informatika",    3.50);

        System.out.println("\n--- Menambahkan Mahasiswa ---");
        repoMahasiswa.tambah(m1);
        repoMahasiswa.tambah(m2);
        repoMahasiswa.tambah(m3);

        // Tampilkan semua mahasiswa
        repoMahasiswa.tampilkanSemua();

        // Cari mahasiswa
        System.out.println("\n--- Mencari Mahasiswa NIM 1102021 ---");
        Mahasiswa cariM = repoMahasiswa.cari("1102021");
        if (cariM != null) cariM.tampilkanInfo();

        // Hapus mahasiswa
        System.out.println("\n--- Menghapus Mahasiswa NIM 1102022 ---");
        repoMahasiswa.hapus("1102022");
        repoMahasiswa.tampilkanSemua();

        // ─── Repository Dosen ────────────────────────────
        Repository<Dosen> repoDosen = new Repository<>("Data Dosen");

        Dosen d1 = new Dosen("D001", "Yulhan Wahyudin", 45, "Pemrograman Java", "M.Kom");
        Dosen d2 = new Dosen("D002", "Rina Susanti",    38, "Basis Data",       "M.T");

        System.out.println("\n--- Menambahkan Dosen ---");
        repoDosen.tambah(d1);
        repoDosen.tambah(d2);

        repoDosen.tampilkanSemua();

        // ─── Polymorphism Demo ───────────────────────────
        System.out.println("\n====== DEMO POLYMORPHISM ======");
        Orang[] wargaKampus = { m1, m2, d1, d2 };
        for (Orang o : wargaKampus) {
            // Setiap objek memanggil tampilkanInfo() milik masing-masing
            System.out.println(o); // memanggil toString() dari Orang
        }

        System.out.println("\n====== SUMMARY ======");
        System.out.println("Total Mahasiswa : " + repoMahasiswa.jumlahData());
        System.out.println("Total Dosen     : " + repoDosen.jumlahData());
        System.out.println("=== Program Selesai ===");
    }
}
