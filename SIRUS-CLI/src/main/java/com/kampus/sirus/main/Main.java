package com.kampus.sirus.main;

import com.kampus.sirus.dao.BookingDAO;
import com.kampus.sirus.dao.RuanganDAO;
import com.kampus.sirus.exception.RuanganSudahDipakaiException;
import com.kampus.sirus.exception.RuanganTidakDitemukanException;
import com.kampus.sirus.model.Booking;
import com.kampus.sirus.model.Dosen;
import com.kampus.sirus.model.Mahasiswa;
import com.kampus.sirus.model.Pengguna;
import com.kampus.sirus.model.Ruangan;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

/**
 * Class utama aplikasi SIRUS-CLI.
 * Menampilkan menu interaktif berbasis teks (CLI).
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RuanganDAO ruanganDAO = new RuanganDAO();
    private static final BookingDAO bookingDAO = new BookingDAO();

    public static void main(String[] args) {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenuUtama();
            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    menuKelolaRuangan();
                    break;
                case "2":
                    menuBookingRuangan();
                    break;
                case "3":
                    menuLaporanBooking();
                    break;
                case "0":
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan SIRUS-CLI.");
                    break;
                default:
                    System.out.println(">> Pilihan tidak valid.\n");
            }
        }
        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println("==================================================");
        System.out.println("     SIRUS-CLI - Sistem Reservasi Ruang Diskusi");
        System.out.println("==================================================");
        System.out.println("1. Kelola Ruangan");
        System.out.println("2. Booking Ruangan");
        System.out.println("3. Laporan Booking");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    // ================= MENU 1: KELOLA RUANGAN =================
    private static void menuKelolaRuangan() {
        System.out.println("\n--- Menu Kelola Ruangan ---");
        System.out.println("1. Tambah Ruangan");
        System.out.println("2. Lihat Daftar Ruangan");
        System.out.println("0. Kembali");
        System.out.print("Pilih sub menu: ");
        String sub = scanner.nextLine().trim();

        switch (sub) {
            case "1":
                tambahRuangan();
                break;
            case "2":
                lihatDaftarRuangan();
                break;
            case "0":
                break;
            default:
                System.out.println(">> Pilihan tidak valid.\n");
        }
    }

    private static void tambahRuangan() {
        try {
            System.out.print("Nama ruangan: ");
            String nama = scanner.nextLine().trim();
            System.out.print("Kapasitas: ");
            int kapasitas = Integer.parseInt(scanner.nextLine().trim());

            ruanganDAO.tambahRuangan(nama, kapasitas);
            System.out.println(">> Ruangan berhasil ditambahkan.\n");

        } catch (NumberFormatException e) {
            System.out.println(">> Kapasitas harus berupa angka.\n");
        } catch (SQLException e) {
            System.out.println(">> Terjadi kesalahan database: " + e.getMessage() + "\n");
        }
    }

    private static void lihatDaftarRuangan() {
        try {
            List<Ruangan> daftar = ruanganDAO.getAllRuangan();
            if (daftar.isEmpty()) {
                System.out.println(">> Belum ada data ruangan.\n");
                return;
            }
            System.out.println("\n--- Daftar Ruangan ---");
            for (Ruangan r : daftar) {
                System.out.println(r);
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println(">> Terjadi kesalahan database: " + e.getMessage() + "\n");
        }
    }

    // ================= MENU 2: BOOKING RUANGAN =================
    private static void menuBookingRuangan() {
        System.out.println("\n--- Menu Booking Ruangan ---");
        System.out.println("1. Booking Ruangan");
        System.out.println("2. Selesaikan / Batalkan Booking");
        System.out.println("0. Kembali");
        System.out.print("Pilih sub menu: ");
        String sub = scanner.nextLine().trim();

        switch (sub) {
            case "1":
                bookingRuangan();
                break;
            case "2":
                selesaikanBooking();
                break;
            case "0":
                break;
            default:
                System.out.println(">> Pilihan tidak valid.\n");
        }
    }

    private static void bookingRuangan() {
        try {
            System.out.print("ID Ruangan: ");
            int idRuangan = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nama pemesan: ");
            String namaPemesan = scanner.nextLine().trim();

            System.out.print("Jenis pengguna (1=Mahasiswa, 2=Dosen): ");
            String jenis = scanner.nextLine().trim();

            // Polimorfisme: objek Pengguna dibentuk sesuai jenisnya,
            // lalu method yang sama dipanggil namun hasil berbeda.
            Pengguna pengguna;
            if (jenis.equals("2")) {
                pengguna = new Dosen(namaPemesan);
            } else {
                pengguna = new Mahasiswa(namaPemesan);
            }

            System.out.println(">> Prioritas: " + pengguna.getPrioritasBooking()
                    + " | Durasi maksimal booking: " + pengguna.getDurasiMaksimal() + " jam");

            System.out.print("Tanggal booking (yyyy-MM-dd): ");
            Date tanggal = Date.valueOf(scanner.nextLine().trim());

            System.out.print("Jam mulai (HH:mm:ss): ");
            Time jamMulai = Time.valueOf(scanner.nextLine().trim());

            System.out.print("Jam selesai (HH:mm:ss): ");
            Time jamSelesai = Time.valueOf(scanner.nextLine().trim());

            Booking booking = new Booking(idRuangan, pengguna.getNama(),
                    pengguna.getClass().getSimpleName(), tanggal, jamMulai, jamSelesai);

            bookingDAO.bookingRuangan(booking);
            System.out.println(">> Booking berhasil. Status ruangan otomatis menjadi 'Dipakai'.\n");

        } catch (NumberFormatException e) {
            System.out.println(">> Input ID ruangan harus berupa angka.\n");
        } catch (IllegalArgumentException e) {
            System.out.println(">> Format tanggal/jam tidak valid.\n");
        } catch (RuanganTidakDitemukanException | RuanganSudahDipakaiException e) {
            System.out.println(">> " + e.getMessage() + "\n");
        } catch (SQLException e) {
            System.out.println(">> Terjadi kesalahan database: " + e.getMessage() + "\n");
        }
    }

    private static void selesaikanBooking() {
        try {
            System.out.print("ID Booking: ");
            int idBooking = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Status baru (Selesai/Dibatalkan): ");
            String status = scanner.nextLine().trim();

            bookingDAO.ubahStatusBooking(idBooking, status);
            System.out.println(">> Status booking diperbarui. Status ruangan otomatis kembali 'Tersedia'.\n");

        } catch (NumberFormatException e) {
            System.out.println(">> ID booking harus berupa angka.\n");
        } catch (SQLException e) {
            System.out.println(">> Terjadi kesalahan database: " + e.getMessage() + "\n");
        }
    }

    // ================= MENU 3: LAPORAN BOOKING =================
    private static void menuLaporanBooking() {
        System.out.println("\n--- Menu Laporan Booking ---");
        System.out.println("1. Lihat Riwayat Booking");
        System.out.println("2. Lihat Total Booking per Ruangan");
        System.out.println("0. Kembali");
        System.out.print("Pilih sub menu: ");
        String sub = scanner.nextLine().trim();

        switch (sub) {
            case "1":
                lihatRiwayatBooking();
                break;
            case "2":
                lihatTotalBooking();
                break;
            case "0":
                break;
            default:
                System.out.println(">> Pilihan tidak valid.\n");
        }
    }

    private static void lihatRiwayatBooking() {
        try {
            List<String> riwayat = bookingDAO.getRiwayatBooking();
            if (riwayat.isEmpty()) {
                System.out.println(">> Belum ada data booking.\n");
                return;
            }
            System.out.println("\n--- Riwayat Booking (dari view daftar_booking) ---");
            for (String baris : riwayat) {
                System.out.println(baris);
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println(">> Terjadi kesalahan database: " + e.getMessage() + "\n");
        }
    }

    private static void lihatTotalBooking() {
        try {
            System.out.print("ID Ruangan: ");
            int idRuangan = Integer.parseInt(scanner.nextLine().trim());

            int total = ruanganDAO.getTotalBooking(idRuangan);
            System.out.println(">> Total booking untuk ruangan ID " + idRuangan + " adalah: " + total + "\n");

        } catch (NumberFormatException e) {
            System.out.println(">> ID ruangan harus berupa angka.\n");
        } catch (SQLException e) {
            System.out.println(">> Terjadi kesalahan database: " + e.getMessage() + "\n");
        }
    }
}
