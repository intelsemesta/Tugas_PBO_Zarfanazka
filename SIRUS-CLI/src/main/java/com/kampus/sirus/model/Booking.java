package com.kampus.sirus.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Class Booking merepresentasikan data transaksi booking ruangan.
 * Seluruh atribut private, diakses lewat getter/setter (ENKAPSULASI).
 */
public class Booking {

    private int idBooking;
    private int idRuangan;
    private String namaPemesan;
    private String jenisPengguna;
    private Date tanggal;
    private Time jamMulai;
    private Time jamSelesai;
    private String status;

    public Booking() {
    }

    public Booking(int idRuangan, String namaPemesan, String jenisPengguna,
                    Date tanggal, Time jamMulai, Time jamSelesai) {
        this.idRuangan = idRuangan;
        this.namaPemesan = namaPemesan;
        this.jenisPengguna = jenisPengguna;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.status = "Aktif";
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getJenisPengguna() {
        return jenisPengguna;
    }

    public void setJenisPengguna(String jenisPengguna) {
        this.jenisPengguna = jenisPengguna;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Time getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Time jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Time getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Time jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
