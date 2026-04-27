package com.kampus.model;

import com.kampus.abstrak.Orang;

// Inheritance: Mahasiswa extends Orang
public class Mahasiswa extends Orang {

    private String nim;
    private String jurusan;
    private double ipk;

    public Mahasiswa(String nim, String nama, int usia, String jurusan, double ipk) {
        super(nim, nama, usia); // memanggil constructor Orang
        this.nim     = nim;
        this.jurusan = jurusan;
        this.ipk     = ipk;
    }

    // Getter & Setter
    public String getNim()     { return nim;     }
    public String getJurusan() { return jurusan; }
    public double getIpk()     { return ipk;     }
    public void   setIpk(double ipk) { this.ipk = ipk; }

    // Polymorphism: override getPeran()
    @Override
    public String getPeran() {
        return "Mahasiswa";
    }

    // Polymorphism: override tampilkanInfo()
    @Override
    public void tampilkanInfo() {
        System.out.println("----------------------------------");
        System.out.println("  MAHASISWA");
        System.out.println("  NIM     : " + nim);
        System.out.println("  Nama    : " + getNama());
        System.out.println("  Usia    : " + getUsia() + " tahun");
        System.out.println("  Jurusan : " + jurusan);
        System.out.println("  IPK     : " + ipk);
        System.out.println("----------------------------------");
    }
}
