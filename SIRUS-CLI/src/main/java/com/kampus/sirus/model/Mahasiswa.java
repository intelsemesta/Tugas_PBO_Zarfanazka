package com.kampus.sirus.model;

/**
 * Subclass Mahasiswa, mewarisi Pengguna (INHERITANCE).
 * Memiliki aturan durasi booking dan prioritas tersendiri (POLIMORFISME).
 */
public class Mahasiswa extends Pengguna {

    public Mahasiswa(String nama) {
        super(nama);
    }

    @Override
    public int getDurasiMaksimal() {
        return 2; // Mahasiswa maksimal booking 2 jam
    }

    @Override
    public String getPrioritasBooking() {
        return "Reguler";
    }
}
