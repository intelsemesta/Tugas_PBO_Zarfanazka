package com.kampus.abstrak;

import com.kampus.interfaces.Identitas;

// Abstract class mengimplementasikan Identitas
// Menerapkan konsep: Abstraction + Encapsulation
public abstract class Orang implements Identitas {

    // Encapsulation: field private
    private String id;
    private String nama;
    private int    usia;

    public Orang(String id, String nama, int usia) {
        this.id   = id;
        this.nama = nama;
        this.usia = usia;
    }

    // Getter (Encapsulation)
    @Override
    public String getId()   { return id;   }
    @Override
    public String getNama() { return nama; }
    public int    getUsia() { return usia; }

    // Setter
    public void setNama(String nama) { this.nama = nama; }
    public void setUsia(int usia)    { this.usia = usia; }

    // Abstract method → wajib di-override di subclass (Polymorphism)
    public abstract String getPeran();

    @Override
    public String toString() {
        return "[" + getPeran() + "] ID=" + id + ", Nama=" + nama + ", Usia=" + usia;
    }
}
