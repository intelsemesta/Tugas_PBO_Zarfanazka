package com.kampus.model;

import com.kampus.abstrak.Orang;

// Inheritance: Dosen extends Orang
public class Dosen extends Orang {

    private String nip;
    private String mataKuliah;
    private String gelar;

    public Dosen(String nip, String nama, int usia, String mataKuliah, String gelar) {
        super(nip, nama, usia);
        this.nip        = nip;
        this.mataKuliah = mataKuliah;
        this.gelar      = gelar;
    }

    // Getter & Setter
    public String getNip()        { return nip;        }
    public String getMataKuliah() { return mataKuliah; }
    public String getGelar()      { return gelar;      }

    // Polymorphism: override getPeran()
    @Override
    public String getPeran() {
        return "Dosen";
    }

    // Polymorphism: override tampilkanInfo()
    @Override
    public void tampilkanInfo() {
        System.out.println("----------------------------------");
        System.out.println("  DOSEN");
        System.out.println("  NIP        : " + nip);
        System.out.println("  Nama       : " + gelar + " " + getNama());
        System.out.println("  Usia       : " + getUsia() + " tahun");
        System.out.println("  Mata Kuliah: " + mataKuliah);
        System.out.println("----------------------------------");
    }
}
