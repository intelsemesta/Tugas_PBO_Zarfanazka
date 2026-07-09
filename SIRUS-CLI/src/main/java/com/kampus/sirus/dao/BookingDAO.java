package com.kampus.sirus.dao;

import com.kampus.sirus.exception.RuanganSudahDipakaiException;
import com.kampus.sirus.exception.RuanganTidakDitemukanException;
import com.kampus.sirus.model.Booking;
import com.kampus.sirus.model.Ruangan;
import com.kampus.sirus.util.KoneksiDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk entitas Booking.
 * Menangani proses booking ruangan serta pengambilan data dari view daftar_booking.
 */
public class BookingDAO {

    private final RuanganDAO ruanganDAO = new RuanganDAO();

    /**
     * Melakukan booking ruangan.
     * Validasi status ruangan dilakukan sebelum insert, memanfaatkan
     * custom exception jika ruangan tidak ditemukan atau sedang dipakai.
     * Setelah insert berhasil, trigger di database otomatis mengubah
     * status ruangan menjadi "Dipakai".
     */
    public void bookingRuangan(Booking booking) throws SQLException,
            RuanganTidakDitemukanException, RuanganSudahDipakaiException {

        Ruangan ruangan = ruanganDAO.getRuanganById(booking.getIdRuangan());

        if ("Dipakai".equalsIgnoreCase(ruangan.getStatus())) {
            throw new RuanganSudahDipakaiException(
                    "Ruangan " + ruangan.getNamaRuangan() + " sedang dipakai, silakan pilih ruangan lain.");
        }

        String sql = "INSERT INTO booking (id_ruangan, nama_pemesan, jenis_pengguna, tanggal, jam_mulai, jam_selesai, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'Aktif')";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getIdRuangan());
            stmt.setString(2, booking.getNamaPemesan());
            stmt.setString(3, booking.getJenisPengguna());
            stmt.setDate(4, booking.getTanggal());
            stmt.setTime(5, booking.getJamMulai());
            stmt.setTime(6, booking.getJamSelesai());
            stmt.executeUpdate();
        }
    }

    /**
     * Mengubah status booking menjadi Selesai/Dibatalkan.
     * Trigger di database otomatis mengembalikan status ruangan menjadi "Tersedia".
     */
    public void ubahStatusBooking(int idBooking, String statusBaru) throws SQLException {
        String sql = "UPDATE booking SET status = ? WHERE id_booking = ?";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, statusBaru);
            stmt.setInt(2, idBooking);
            stmt.executeUpdate();
        }
    }

    /**
     * Mengambil seluruh riwayat booking dari view daftar_booking.
     */
    public List<String> getRiwayatBooking() throws SQLException {
        List<String> riwayat = new ArrayList<>();
        String sql = "SELECT * FROM daftar_booking";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String baris = "ID: " + rs.getInt("id_booking")
                        + " | Ruangan: " + rs.getString("nama_ruangan")
                        + " | Pemesan: " + rs.getString("nama_pemesan")
                        + " (" + rs.getString("jenis_pengguna") + ")"
                        + " | Tanggal: " + rs.getDate("tanggal")
                        + " | Jam: " + rs.getTime("jam_mulai") + " - " + rs.getTime("jam_selesai")
                        + " | Status: " + rs.getString("status");
                riwayat.add(baris);
            }
        }
        return riwayat;
    }
}
