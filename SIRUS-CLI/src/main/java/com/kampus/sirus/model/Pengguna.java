package com.kampus.sirus.model;

/**
 * Superclass abstrak untuk pengguna aplikasi SIRUS-CLI.
 * Diturunkan menjadi Mahasiswa dan Dosen (konsep INHERITANCE).
 */
public abstract class Pengguna {

    // Atribut private -> konsep ENKAPSULASI
    private String nama;

    public Pengguna(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Durasi maksimal booking dalam satuan jam.
     * Akan di-override berbeda oleh setiap subclass -> konsep POLIMORFISME
     */
    public abstract int getDurasiMaksimal();

    /**
     * Kategori prioritas booking pengguna.
     * Akan di-override berbeda oleh setiap subclass -> konsep POLIMORFISME
     */
    public abstract String getPrioritasBooking();
}
