package com.kampus.collection;

import com.kampus.abstrak.Orang;
import java.util.ArrayList;
import java.util.List;

// Generic Repository: T harus turunan dari Orang
// Menerapkan konsep: Generic + Collection
public class Repository<T extends Orang> {

    private String      namaRepository;
    private List<T>     dataList;

    public Repository(String namaRepository) {
        this.namaRepository = namaRepository;
        this.dataList        = new ArrayList<>();
    }

    // Tambah data
    public void tambah(T item) {
        dataList.add(item);
        System.out.println("[+] " + item.getNama() + " berhasil ditambahkan ke " + namaRepository);
    }

    // Hapus data berdasarkan ID
    public boolean hapus(String id) {
        for (T item : dataList) {
            if (item.getId().equals(id)) {
                dataList.remove(item);
                System.out.println("[-] Data dengan ID " + id + " berhasil dihapus.");
                return true;
            }
        }
        System.out.println("[!] Data dengan ID " + id + " tidak ditemukan.");
        return false;
    }

    // Cari data berdasarkan ID
    public T cari(String id) {
        for (T item : dataList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    // Tampilkan semua data
    public void tampilkanSemua() {
        System.out.println("\n====== " + namaRepository + " (" + dataList.size() + " data) ======");
        if (dataList.isEmpty()) {
            System.out.println("  (kosong)");
        } else {
            for (T item : dataList) {
                item.tampilkanInfo(); // Polymorphism!
            }
        }
    }

    // Jumlah data
    public int jumlahData() { return dataList.size(); }

    public String getNamaRepository() { return namaRepository; }
}
