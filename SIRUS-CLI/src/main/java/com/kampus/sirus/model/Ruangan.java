package com.kampus.sirus.model;

/**
 * Class Ruangan merepresentasikan data ruang diskusi.
 * Seluruh atribut private, diakses lewat getter/setter (ENKAPSULASI).
 */
public class Ruangan {

    private int idRuangan;
    private String namaRuangan;
    private int kapasitas;
    private String status;

    public Ruangan() {
    }

    public Ruangan(int idRuangan, String namaRuangan, int kapasitas, String status) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.kapasitas = kapasitas;
        this.status = status;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + idRuangan + "] " + namaRuangan +
                " | Kapasitas: " + kapasitas +
                " | Status: " + status;
    }
}
