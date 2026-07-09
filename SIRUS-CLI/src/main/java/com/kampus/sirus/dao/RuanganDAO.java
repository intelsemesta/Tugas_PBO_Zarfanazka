package com.kampus.sirus.dao;

import com.kampus.sirus.exception.RuanganTidakDitemukanException;
import com.kampus.sirus.model.Ruangan;
import com.kampus.sirus.util.KoneksiDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk entitas Ruangan.
 * Bertugas menjembatani aplikasi Java dengan tabel ruangan di database,
 * termasuk memanggil stored procedure dan function.
 */
public class RuanganDAO {

    /**
     * Menambahkan ruangan baru dengan memanggil stored procedure tambah_ruangan().
     */
    public void tambahRuangan(String namaRuangan, int kapasitas) throws SQLException {
        String sql = "{CALL tambah_ruangan(?, ?)}";
        try (Connection conn = KoneksiDatabase.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, namaRuangan);
            stmt.setInt(2, kapasitas);
            stmt.execute();
        }
    }

    /**
     * Mengambil seluruh data ruangan.
     */
    public List<Ruangan> getAllRuangan() throws SQLException {
        List<Ruangan> daftarRuangan = new ArrayList<>();
        String sql = "SELECT * FROM ruangan ORDER BY id_ruangan";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ruangan r = new Ruangan(
                        rs.getInt("id_ruangan"),
                        rs.getString("nama_ruangan"),
                        rs.getInt("kapasitas"),
                        rs.getString("status")
                );
                daftarRuangan.add(r);
            }
        }
        return daftarRuangan;
    }

    /**
     * Mengambil satu data ruangan berdasarkan ID.
     * Melempar RuanganTidakDitemukanException jika data tidak ada (EXCEPTION HANDLING).
     */
    public Ruangan getRuanganById(int idRuangan) throws SQLException, RuanganTidakDitemukanException {
        String sql = "SELECT * FROM ruangan WHERE id_ruangan = ?";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRuangan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Ruangan(
                            rs.getInt("id_ruangan"),
                            rs.getString("nama_ruangan"),
                            rs.getInt("kapasitas"),
                            rs.getString("status")
                    );
                } else {
                    throw new RuanganTidakDitemukanException(
                            "Ruangan dengan ID " + idRuangan + " tidak ditemukan.");
                }
            }
        }
    }

    /**
     * Mengambil total booking suatu ruangan dengan memanggil function total_booking().
     */
    public int getTotalBooking(int idRuangan) throws SQLException {
        String sql = "{? = CALL total_booking(?)}";
        try (Connection conn = KoneksiDatabase.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setInt(2, idRuangan);
            stmt.execute();
            return stmt.getInt(1);
        }
    }
}
