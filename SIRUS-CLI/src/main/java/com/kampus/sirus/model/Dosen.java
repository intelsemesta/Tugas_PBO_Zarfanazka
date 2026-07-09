package com.kampus.sirus.model;

/**
 * Subclass Dosen, mewarisi Pengguna (INHERITANCE).
 * Memiliki aturan durasi booking dan prioritas berbeda dari Mahasiswa (POLIMORFISME).
 */
public class Dosen extends Pengguna {

    public Dosen(String nama) {
        super(nama);
    }

    @Override
    public int getDurasiMaksimal() {
        return 4; // Dosen maksimal booking 4 jam
    }

    @Override
    public String getPrioritasBooking() {
        return "Prioritas";
    }
}
